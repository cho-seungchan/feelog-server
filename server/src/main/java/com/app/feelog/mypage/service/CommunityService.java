// 2025.04.26 조승찬

package com.app.feelog.mypage.service;

import com.app.feelog.domain.vo.*;
import com.app.feelog.mypage.dto.CommunityPostListDTO;
import com.app.feelog.mypage.dto.CommunityPostWriteDTO;
import com.app.feelog.mypage.dto.NotifyCommunityListDTO;
import com.app.feelog.mypage.repository.CommunityDAO;
import com.app.feelog.mypage.repository.MyPageDAO;
import com.app.feelog.mypage.util.CalculateTimeAgo;
import com.app.feelog.repository.CommunityPostDAO;
import com.app.feelog.util.SixRowPagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityService implements ToDTO{

    private final CommunityDAO communityDAO;
    private final MyPageDAO myPageDAO;
    private final CalculateTimeAgo calculateTimeAgo;
    private final CommunityPostWriteDTO communityPostWriteDTO;

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 목록
    public List<CommunityPostListDTO> getCommunityPostList(Long myId, String channelUrl, SixRowPagination pagination) {

        // 페이지네이션 처리
        // pagination.create(communityDAO.getCommunityPostListTotalCount(channelUrl));
        // 커뮤니티 게시글 가져오기
        List<CommunityPostVO> communityList = communityDAO.getCommunityPostList(channelUrl, pagination);

        List<CommunityPostListDTO> resultList = new ArrayList<>();
        communityList.forEach(community -> {
            // 커뮤니티 게시글의 파일 가져오기
            List<CommunityPostFileVO> files = communityDAO.getCommunityPostFiles(community.getId());
            // 작성자 정보 가져오기
            MemberVO member = myPageDAO.getMemberById(community.getMemberId()).orElse(null);
            // 작성자 채널 정보 가져오기
            ChannelVO memberChannel = myPageDAO.getChannelByMemberId(community.getMemberId()).orElse(null);
            // 작성 시간 계산하기
            String timeAgo = calculateTimeAgo.calculateTimeAgo(community.getCreatedDate());
            // 좋아요 건수
            int likes = communityDAO.getLikeCount(community.getId());
            // 댓글 건수
            int replys =communityDAO.getReplyCount(community.getId());
            // 신고 건수
            int reports =communityDAO.getReportCount(community.getId());
            // 내가 포스트에 좋아요 했는지 알아보기
            boolean iLike = communityDAO.getILike(myId, community.getId());
            // 내가 포스트에 신고 했는지 알아보기
            boolean iReport = communityDAO.getIReport(myId, community.getId());

            resultList.add(toCommunityPostListDTO(community, member, files, memberChannel,
                                                    timeAgo,replys, likes, reports, iLike, iReport));
        });

        return resultList;
        // 채널 포스트 목록 가져오기
    }

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 저장
    public void postCommunityPost(String channelUrl, CommunityPostWriteDTO communityPostWriteDTO) {

        // 커뮤니티 포스트 저장
        CommunityPostVO communityPostVO = communityPostWriteDTO.toVO();
        communityDAO.postPost(communityPostVO);
        communityDAO.postCommunityPost(channelUrl, communityPostVO);
        // 커뮤니티 포스트 파일 저장
        if (communityPostWriteDTO.getFiles() != null) {
            communityPostWriteDTO.getFiles().forEach(file -> {
                communityDAO.postFile(file);
                communityDAO.postCommunityPostFile(file, communityPostVO.getId());
            });
        }
    }

    // 2025.04.26  조승찬 :: 포스크 글 읽어오기
    public Optional<CommunityPostWriteDTO> getCommunityPostDetail(Long postId) {

        // 포스트 내용 가져오기
        CommunityPostVO postVO = communityDAO.getCommunityPost(postId).orElse(null);
        // 포스트 파일 가져오기
        List<CommunityPostFileVO> files = communityDAO.getCommunityPostFiles(postId);

        CommunityPostWriteDTO communityPostWriteDTO = new CommunityPostWriteDTO();
        communityPostWriteDTO.setId(postId);
        communityPostWriteDTO.setPostContent(postVO.getPostContent());
        communityPostWriteDTO.setFiles(files);

        return Optional.ofNullable(communityPostWriteDTO);
    }

    // 2025.04.26 조승찬 ::  포스트 내용 수정
    public void updateCommunityPost(CommunityPostWriteDTO communityPostWriteDTO) {
        // 포스트 글 수정
        communityDAO.updateCommunityPost(communityPostWriteDTO.toVO());
        // 포스트 첨부 파일 삭제
        communityDAO.deleteCommunityPostFile(communityPostWriteDTO.getId());
        // 커뮤니티 포스트 파일 저장
        if (communityPostWriteDTO.getFiles() != null) {
            communityPostWriteDTO.getFiles().forEach(file -> {
                communityDAO.postFile(file);
                communityDAO.postCommunityPostFile(file, communityPostWriteDTO.getId());
            });
        }
    }

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 삭제
    public void deleteCommunityPost(Long postId) {
        // 게시글 삭제
        communityDAO.deleteCommunityPost(postId);
        // 게시글 첨부 파일 삭제
        communityDAO.updateCommunityPostFile(postId);
        // 게시글 좋아요 삭제
        communityDAO.deleteCommunityPostLike(postId);
        // 게시글 신고 삭제
        communityDAO.deleteCommunityPostReport(postId);
        // 게시글 댓글 좋아요 삭제
        communityDAO.deleteCommunityPostReplyLike(postId);
        // 게시글 댓글 신고 삭제
        communityDAO.deleteCommunityPostReplyReport(postId);
        // 게시글 댓글 삭제
        communityDAO.deleteCommunityPostReply(postId);
    }

    // 2025.04.27 조승찬 :: 좋아요 건수 가져오기
    public int getLikeCount(Long postId) {
        return communityDAO.getLikeCount(postId);
    }

    // 2025.04.27 조승찬 :: 좋아요 저장
    public void postCommunityPostLike(Long memberId,Long postId) {
        // 슈퍼키 저장
        LikeVO likeVO = new LikeVO();
        communityDAO.postLike(likeVO);
        communityDAO.postCommunityPostLike(likeVO.getId(),memberId, postId);
    }

    // 2025.04.27 조승찬 :: 좋아요 취소
    public void cancelCommunityPostLike(Long memberId, Long postId) {
        communityDAO.cancelCommunityPostLike(memberId, postId);
    }

    // 2025.04.27 조승찬 :: 신고 건수 가져오기
    public int getReportCount(Long postId) {
        return communityDAO.getReportCount(postId);
    }

    // 2025.04.27 조승찬 :: 신고 처리
    public void postCommunityPostReport(Long memberId, Long postId) {
        // 슈퍼키 저장
        ReportVO reportVO = new ReportVO();
        communityDAO.postReport(reportVO);
        communityDAO.postCommunityPostReport(reportVO.getId(),memberId, postId);

    }

    // 2025.04.27 조승찬 :: 신고 취소
    public void cancelCommunityPostReport(Long memberId, Long postId) {
        communityDAO.cancelCommunityPostReport(memberId, postId);
    }
}
