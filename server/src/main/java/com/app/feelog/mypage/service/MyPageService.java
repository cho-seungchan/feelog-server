// 2021.04.21 조승찬
package com.app.feelog.mypage.service;

import com.app.feelog.domain.dto.ChannelDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.vo.ChannelVO;
import com.app.feelog.domain.vo.CommunityPostVO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.mypage.dto.NotifyCommunityListDTO;
import com.app.feelog.mypage.dto.NotifyReplyListDTO;
import com.app.feelog.mypage.repository.MyPageDAO;
import com.app.feelog.util.SixRowPagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
            String timAgo = calculateTimeAgo(communityPost.getCreatedDate());

            resultList.add(toNotifyCommunityListDTO(communityPost, member, memberChannel, timAgo));
        });

        return resultList;
    }

    // 2025.04.23 조승찬 :: 알림 메뉴 중 포스트 댓글 목록
    public List<NotifyReplyListDTO> getNotifyReplyList(Long memberId, SixRowPagination pagination) {

        pagination.create(myPageDAO.getNotifyReplyListTotalCount(memberId));

//        // 커뮤니티 게시글 가져오기
//        List<CommunityPostVO> communityPostList = myPageDAO.getNotifyCommunityList(memberId, pagination);
//
//        List<NotifyReplyListDTO> resultList = new ArrayList<>();
//        communityPostList.forEach(communityPost -> {
//            // 작성자 정보 가져오기
//            MemberVO member = myPageDAO.getMemberById(communityPost.getMemberId()).orElse(null);
//            // 작성자 채널 정보 가져오기
//            ChannelVO memberChannel = myPageDAO.getChannelByMemberId(communityPost.getMemberId()).orElse(null);
//            // 작성 시간 계산하기
//            String timAgo = calculateTimeAgo(communityPost.getCreatedDate());
//
//            resultList.add(toNotifyReplyListDTO(communityPost, member, memberChannel, timAgo));
//        });

        return resultList;
    }

    // 2025.04.23 조승찬 :: create data의 작성시점 변환하기
    public String calculateTimeAgo(String createdDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime createdDateTime = LocalDateTime.parse(createdDate, formatter);
        LocalDateTime now = LocalDateTime.now();

        long days = ChronoUnit.DAYS.between(createdDateTime, now);
        long hours = ChronoUnit.HOURS.between(createdDateTime, now) % 24;
        long minutes = ChronoUnit.MINUTES.between(createdDateTime, now) % 60;

        if (days > 0) {
            return days + "일 전";
        } else if (hours > 0) {
            return hours + "시간 전";
        } else if (minutes > 0) {
            return minutes + "분 전";
        } else {
            return "방금 전";
        }
    }

}
