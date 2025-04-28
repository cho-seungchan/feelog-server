// 2025.04.26 조승찬

package com.app.feelog.mypage.repository;

import com.app.feelog.domain.vo.*;
import com.app.feelog.mypage.mapper.CommunityMapper;
import com.app.feelog.util.SixRowPagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CommunityDAO {

    private final CommunityMapper communityMapper;

    // 2025.04.26 조승찬 :: 페이지네이션을 위한 개인채널 커뮤니티 글 전체 건수
    public int getCommunityPostListTotalCount(String channelUrl) {
        return communityMapper.getCommunityPostListTotalCount(channelUrl);
    }

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 목록
    public List<CommunityPostVO> getCommunityPostList(String channelUrl, SixRowPagination pagination) {
        return communityMapper.getCommunityPostList(channelUrl, pagination);
    }

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 첨부 파일 목록
    public List<CommunityPostFileVO> getCommunityPostFiles(Long postId) {
        return communityMapper.getCommunityPostFiles(postId);
    }

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 좋아요 수
    public int getLikeCount(Long postId) {
        return communityMapper.getLikeCount(postId);
    }

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 댓글 수
    public int getReplyCount(Long postId) {
        return communityMapper.getReplyCount(postId);
    }

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 신고 수
    public int getReportCount(Long postId) {
        return communityMapper.getReportCount(postId);
    }

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글에 내가 좋아요 했는지 여부
    public boolean getILike(Long memberId, Long postId) {
        return communityMapper.getILike(memberId, postId);
    }

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글에 내가 신고 했는지 여부
    public boolean getIReport(Long memberId, Long postId) {
        return communityMapper.getIReport(memberId, postId);
    }

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 슈퍼키 테이블에 저장
    public void postPost(CommunityPostVO postVO) {
        communityMapper.postPost(postVO);
    }

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 저장
    public void postCommunityPost(String channelUrl, CommunityPostVO postVO) {
        communityMapper.postCommunityPost(channelUrl, postVO);
    }

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 파일 슈퍼키 테이블 저장
    public void postFile(CommunityPostFileVO file) {
        communityMapper.postFile(file);
        log.info("file info ::  "+file.toString());
    }

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 파일 저장
    public void postCommunityPostFile(CommunityPostFileVO file, Long postId) {
        communityMapper.postCommunityPostFile(file, postId);
    }

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 읽어오기
    public Optional<CommunityPostVO> getCommunityPost(Long postId) {
        return Optional.ofNullable(
                communityMapper.getCommunityPost(postId)
                        .orElse(null));
    }

    // 2025.04.26 조승찬 ::  포스트 내용 수정
    public void updateCommunityPost(CommunityPostVO postVO) {
        communityMapper.updateCommunityPost(postVO);
    }

    // 2025.04.26 조승찬 ::  포스트 첨부 파일 삭제
    public void deleteCommunityPostFile(Long postId) {
        communityMapper.deleteCommunityPostFile(postId);
    }

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 삭제
    public void deleteCommunityPost(Long id) {
        communityMapper.deleteCommunityPost(id);
    }

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 첨부파일 삭제
    public void updateCommunityPostFile(Long postId) {
        communityMapper.updateCommunityPostFile(postId);
    }

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 댓글 삭제
    public void deleteCommunityPostReply(Long postId) {
        communityMapper.deleteCommunityPostReply(postId);
    }

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 좋아요 삭제
    public void deleteCommunityPostLike(Long postId) {
        communityMapper.deleteCommunityPostLike(postId);
    }

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 신고 삭제
    public void deleteCommunityPostReport(Long postId) {
        communityMapper.deleteCommunityPostReport(postId);
    }

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 댓글 좋아요 삭제
    public void deleteCommunityPostReplyLike(Long postId) {
        communityMapper.deleteCommunityPostReplyLike(postId);
    }

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 댓글 신고 삭제
    public void deleteCommunityPostReplyReport(Long postId) {
        communityMapper.deleteCommunityPostReplyReport(postId);
    }

    // 2025.04.27 조승찬 :: 좋아요 슈퍼키 입력
    public Long postLike(LikeVO likeVO) {
        return communityMapper.postLike(likeVO);
    }

    // 2025.04.27 조승찬 :: 좋아요 저장
    public void postCommunityPostLike(Long id, Long memberId, Long postId) {
        communityMapper.postCommunityPostLike(id, memberId, postId);
    }

    // 2025.04.27 조승찬 :: 좋아요 취소
    public void cancelCommunityPostLike(Long memberId, Long postId) {
        communityMapper.cancelCommunityPostLike(memberId, postId);
    }

    // 2025.04.27 조승찬 :: 신고 슈퍼키 입력
    public void postReport(ReportVO reportVO) {
        communityMapper.postReport(reportVO);
    }

    // 2025.04.27 조승찬 :: 신고 저장
    public void postCommunityPostReport(Long id, Long memberId, Long postId) {
        communityMapper.postCommunityPostReport(id, memberId, postId);

    }

    // 2025.04.27 조승찬 :: 신고 취소
    public void cancelCommunityPostReport(Long memberId, Long postId) {
        communityMapper.cancelCommunityPostReport(memberId, postId);
    }

    // 2025.04.28 조승찬 :: 커뮤니티 게시글 댓글 정보 가져오기
    public List<CommunityPostReplyVO> getCommunityPostReply(Long postId) {
        return communityMapper.getCommunityPostReply(postId);
    }

    // 2025.04.28  조승찬 :: 댓글 슈퍼키 저장
    public void postReply(CommunityPostReplyVO replyVO) {
        communityMapper.postReply(replyVO);
    }

    // 2025.04.28  조승찬 :: 댓글 저장
    public void postCommunityPostReply(CommunityPostReplyVO replyVO) {
        communityMapper.postCommunityPostReply(replyVO);
    }
}
