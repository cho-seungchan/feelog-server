// 2021.04.21 조승찬
package com.app.feelog.mypage.service;

import com.app.feelog.domain.dto.ChannelDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.SubscribeDTO;
import com.app.feelog.domain.vo.*;
import com.app.feelog.mypage.dto.*;
import com.app.feelog.mypage.repository.MyPageDAO;
import com.app.feelog.mypage.util.CalculateTimeAgo;
import com.app.feelog.util.SixRowPagination;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyPageService implements ToDTO {

    private final MyPageDAO myPageDAO;
    private final CalculateTimeAgo calculateTimeAgo;

    // 2025.04.21  조승찬 :: 프로필 수정
    public void postSettingProfile(MemberDTO memberDTO) {
        myPageDAO.postSettingProfile(memberDTO.toVO());
    }

    // 2025.04.21 조승찬 :: 아이디로 멤버 정보 가져오기
    public MemberDTO getMemberById(Long id) {

        return toMemberDTO(myPageDAO.getMemberById(id)
                        .orElse(null));
    }

    // 2025.04.22  조승찬 :: 알림정보 수정
    public void postSettingNotify(MemberDTO memberDTO) {
        myPageDAO.postSettingNotify(memberDTO.toVO());
    }

    // 2025.04.22  조승찬 :: 채널 정보 가져오기
    public Optional<ChannelDTO> getChannelByUrl(String channelUrl) {

        return Optional.ofNullable(
                toChannelDTO(myPageDAO.getChannelByUrl(channelUrl)
                        .orElse(null)));
    }

    // 2025.04.22 조승찬 :: 채널 생성하기
    public void postMakingChannel(ChannelDTO channelDTO) {
        myPageDAO.postMakingChannel(channelDTO.toVO());
    }

    // 2025.04.23 조승찬 :: 채널 수정하기
    public void postUpdateChannel(ChannelDTO channelDTO) {
        myPageDAO.postUpdateChannel(channelDTO.toVO());
    }

    // 2025.04.23 조승찬 :: 채널 삭제하기
    public void postDeleteChannel(Long id) {
        myPageDAO.postDeleteChannel(id);
    }

    // 2025.04.23 조승찬 :: 닉네임 중복체크
    public Optional<MemberDTO> getMemberByNickname(String memberNickname) {
        return Optional.ofNullable(
                toMemberDTO(myPageDAO.getMemberByNickname(memberNickname)
                        .orElse(null)));
    }

    // 2025.04.23 조승찬 :: 알림 메뉴 중 커뮤니티 목록
    public List<NotifyCommunityListDTO> getNotifyCommunityList(Long memberId, SixRowPagination pagination) {

        pagination.create(myPageDAO.getNotifyCommunityListTotalCount(memberId));
        // 커뮤니티 게시글 가져오기
        List<CommunityPostVO> communityPostList = myPageDAO.getNotifyCommunityList(memberId, pagination);

        List<NotifyCommunityListDTO> resultList = new ArrayList<>();
        communityPostList.forEach(communityPost -> {
            // 작성자 정보 가져오기
            MemberVO member = myPageDAO.getMemberById(communityPost.getMemberId()).orElse(null);
            // 작성자 채널 정보 가져오기
            ChannelVO memberChannel = myPageDAO.getChannelByMemberId(communityPost.getMemberId()).orElse(null);
            // 작성 시간 계산하기
            String timeAgo = calculateTimeAgo.calculateTimeAgo(communityPost.getCreatedDate());

            resultList.add(toNotifyCommunityListDTO(communityPost, member, memberChannel, timeAgo));
        });

        return resultList;
    }


    // 2025.04.23 조승찬 :: 알림 메뉴 중 포스트 댓글 목록
    public List<NotifyReplyListDTO> getNotifyReplyList(Long memberId, SixRowPagination pagination) {

        pagination.create(myPageDAO.getNotifyReplyListTotalCount(memberId));

        // 포스트 댓글 가져오기
        List<ChannelPostReplyVO> channelPostReplyList = myPageDAO.getNotifyReplyList(memberId, pagination);

        List<NotifyReplyListDTO> resultList = new ArrayList<>();
        channelPostReplyList.forEach(channelPostReply -> {
            // 작성자 정보 가져오기
            MemberVO member = myPageDAO.getMemberById(channelPostReply.getMemberId()).orElse(null);
            // 작성자 채널 정보 가져오기
            ChannelVO memberChannel = myPageDAO.getChannelByMemberId(channelPostReply.getMemberId()).orElse(null);
            // 작성 시간 계산하기
            String timeAgo = calculateTimeAgo.calculateTimeAgo(channelPostReply.getCreatedDate());
            // 포스트 정보 가져오기
            PostVO post = myPageDAO.getPostById(channelPostReply.getPostId()).orElse(null);

            resultList.add(toNotifyReplyListDTO(channelPostReply, member, memberChannel, timeAgo, post.getPostTitle()));
        });

        return resultList;
    }

    // 2025.04.24 조승찬 :: 알림 메뉴 중 관리자 공지 목록을 위해 일기 정보 가져오기
    public Optional<DiaryVO> getDiaryByMemberId(Long memberId) {
        return Optional.ofNullable(myPageDAO.getDiaryByMemberId(memberId)
                        .orElse(null));
    }

    // 2025.04.24 조승찬 :: 50점 미만이면 서울시 열린 광장 시설 정보 가져오기
    @SneakyThrows
    public List<NotifyAdminListDTO> getFacilityInfo() throws IOException {

        int startIndex = 1;   // 요청 시작위치
        int endIndex =  500; // 요청 종료위치
        boolean continueProcessing = true; // 반복 여부

        List<NotifyAdminListDTO> admins = new ArrayList<>();
        while (continueProcessing) {
            // URL 설정
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
            urlBuilder.append("/" + URLEncoder.encode("70424f77426a666b3130336468497559", "UTF-8")); // 인증키
            urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); // 요청파일타입
            urlBuilder.append("/" + URLEncoder.encode("fcltOpenInfo", "UTF-8")); // 서비스명
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(startIndex), "UTF-8")); // 요청 시작위치
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(endIndex), "UTF-8")); // 요청 종료위치

            // HTTP 요청
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            // JSON 데이터 처리
            String jsonData = sb.toString();
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject culturalEventInfo = jsonObject.getJSONObject("fcltOpenInfo");
            String resultCode = culturalEventInfo.getJSONObject("RESULT").getString("CODE");

            // "CODE" 값이 "INFO-000"이 아니면 중단
            if (!"INFO-000".equals(resultCode)) {
                System.out.println(resultCode+" 코드가 감지되어 작업을 중단합니다.");
                break;
            }

            // 서울시 데이타를 DTO로 옮기기
            JSONArray rows = culturalEventInfo.getJSONArray("row");
            for (int i = 0; i < rows.length(); i++) {
                JSONObject row = rows.getJSONObject(i);
                if (row.getString("FCLT_KIND_DTL_NM").trim().contains("정신")){

                    NotifyAdminListDTO  admin = new NotifyAdminListDTO();
                    admin.setFacilityName(row.getString("FCLT_NM"));
                    admin.setDistrictName(row.getString("JRSD_SGG_NM"));
                    admin.setFacilityKindName(row.getString("FCLT_KIND_NM"));
                    admin.setFacilityAddress(row.getString("FCLT_ADDR"));
                    admin.setFacilityTellNo(row.getString("FCLT_TEL_NO"));

                    admins.add(admin);
                }

            }

            // 다음 요청 범위 설정
            startIndex += 500;
            endIndex += 500;

            // 전체 데이터 개수 확인
            int totalCount = culturalEventInfo.getInt("list_total_count");
            if (startIndex > totalCount) {
                System.out.println("모든 데이터를 처리했습니다.");
                continueProcessing = false;
            }
        }

        return admins;
    }

    // 2025.04.24 조승찬 :: 구독자 리스트 조회
    public List<NotifySubscribeListDTO> getNotifySubscribe(Long memberId, SixRowPagination pagination) {

        // 페이지 네이션을 위한 총 건수 가져오기
        pagination.create(myPageDAO.getNotifySubscribeTotalCount(memberId));
        // 구독자 채널 정보 가져오기
        List<ChannelVO> channels = myPageDAO.getNotifySubscribe(memberId, pagination);

        // DTO에 멤버 정보 채우기
        List<NotifySubscribeListDTO> subscribes = new ArrayList<>();
        channels.forEach(channel -> {
            // 멤버 정보 가져오기
            MemberVO member = myPageDAO.getMemberById(channel.getMemberId()).orElse(null);
            subscribes.add(toNotifySubscribeListDTO(channel, member));
        });

        return subscribes;
    }

    // 2025.04.24 조승찬 :: 구독 취소
    public void cancelSubscribe(SubscribeDTO subscribeDTO) {
        myPageDAO.cancelSubscribe(subscribeDTO.toVO());
    }

    // 2025.04.24 조승찬 :: 채널정보 가져오기
    public Optional<ChannelDTO> getChannelByMemberId(Long memberId) {
        return Optional.ofNullable(toChannelDTO(myPageDAO.getChannelByMemberId(memberId)
                .orElse(null)));
    };

    // 2025.04.24 조승찬 :: 구독 리스트 조회
    public List<StorageSubscribeListDTO> getStorageSubscribe(Long memberId, SixRowPagination pagination) {

        // 페이지 네이션을 위한 총 건수 가져오기
        pagination.create(myPageDAO.getStorageSubscribeTotalCount(memberId));
        // 구독자 채널 정보 가져오기
        List<ChannelVO> channels = myPageDAO.getStorageSubscribe(memberId, pagination);

        // DTO에 멤버 정보 채우기
        List<StorageSubscribeListDTO> subscribes = new ArrayList<>();
        channels.forEach(channel -> {
            // 멤버 정보 가져오기
            MemberVO member = myPageDAO.getMemberById(channel.getMemberId()).orElse(null);
            subscribes.add(toStorageSubscribeListDTO(channel, member));
        });

        return subscribes;
    }

    // 2025.04.25 조승찬 :: 스크랩 리스트 조회
    public List<StorageScrapListDTO> getStorageScrap(Long memberId, SixRowPagination pagination) {

        // 페이지네이션을 위한 총 건수 가져오기
        pagination.create(myPageDAO.getStorageScrapTotalCount(memberId));

        // 스크랩 목록 가져오기
        List<ChannelPostScrapVO> scraps = myPageDAO.getStorageScrap(memberId, pagination);

        List<StorageScrapListDTO> scrapList = new ArrayList<>();
        scraps.forEach(scrap -> {
            // 멤버정보 가져오기
            MemberVO member = myPageDAO.getMemberById(scrap.getMemberId()).orElse(null);
            // channel post 정보 가져오기
            ChannelPostVO post = myPageDAO.getChannelPostById(scrap.getPostId()).orElse(null);
            // 작성 시간 계산하기
            String timeAgo = calculateTimeAgo.calculateTimeAgo(post.getCreatedDate());
            // 좋아요 건수
            int likes = myPageDAO.getLikeCount(scrap.getPostId());
            // 댓글 건수
            int replys =myPageDAO.getReplyCount(scrap.getPostId());
            // dto로 변환하기
            scrapList.add(toStorageScrapListDTO(scrap, member, post, timeAgo, replys, likes));
        });

        return scrapList;
    };

    // 2025.04.25 스크랩 취소하기
    public void storageOffScrap(Long id) {
        myPageDAO.storageOffScrap(id);
    }

    // 2025.04.25 스크랩 재설정하기
    public void storageOnScrap(Long id) {
        myPageDAO.storageOnScrap(id);
    }

    // 2025.04.25 조승찬 :: 좋아요 리스트 조회
    public List<StorageLikeListDTO> getStorageLike(Long memberId, SixRowPagination pagination) {

        // 페이지네이션을 위한 총 건수 가져오기
        pagination.create(myPageDAO.getStorageLikeTotalCount(memberId));

        // 좋아요 목록 가져오기
        List<ChannelPostLikeVO> list = myPageDAO.getStorageLike(memberId, pagination);

        List<StorageLikeListDTO> likeList = new ArrayList<>();
        list.forEach(like -> {
            // 멤버정보 가져오기
            MemberVO member = myPageDAO.getMemberById(like.getMemberId()).orElse(null);
            // channel post 정보 가져오기
            ChannelPostVO post = myPageDAO.getChannelPostById(like.getPostId()).orElse(null);
            // 작성 시간 계산하기
            String timeAgo = calculateTimeAgo.calculateTimeAgo(post.getCreatedDate());
            // 좋아요 건수
            int likes = myPageDAO.getLikeCount(like.getPostId());
            // 댓글 건수
            int replys =myPageDAO.getReplyCount(like.getPostId());
            // dto로 변환하기
            likeList.add(toStorageLikeListDTO(like, member, post, timeAgo, replys, likes));
        });

        return likeList;
    }

    // 2025.04.25 조승찬 ::  좋아요 취소하기
    public void storageOffLike(Long id) {
        myPageDAO.storageOffLike(id);
    }

    // 2025.04.25 조승찬 ::  좋아요 재설정하기
    public void storageOnLike(Long id) {
        myPageDAO.storageOnLike(id);
    }

    // 2025.04.25 조승찬 ::  좋아요 건수 가져오기
    public int getLikeCount(Long postId) {
        return myPageDAO.getLikeCount(postId);
    }

    // 2025.04.25  조승찬 :: 댓글 목록
    public List<StorageReplyListDTO> getStorageReply(Long memberId, SixRowPagination pagination) {

        // 페이지네이션을 위한 총 건수 가져오기
        pagination.create(myPageDAO.getStorageReplyTotalCount(memberId));

        // 좋아요 목록 가져오기
        List<ChannelPostReplyVO> replies = myPageDAO.getStorageReply(memberId, pagination);

        List<StorageReplyListDTO> replyList = new ArrayList<>();
        replies.forEach(reply -> {
            // channel post 정보 가져오기
            ChannelPostVO post = myPageDAO.getChannelPostById(reply.getPostId()).orElse(null);
            // 작성 시간 계산하기
            String timeAgo = calculateTimeAgo.calculateTimeAgo(post.getCreatedDate());
            // dto로 변환하기
            replyList.add(toStorageReplyListDTO(reply, post, timeAgo));
        });

        return replyList;
    }

    // 2025.04.25 조승찬 :: 댓글 삭제
    public void deleteStorageReply(Long id) {
        myPageDAO.deleteStorageReply(id);
    }
}
