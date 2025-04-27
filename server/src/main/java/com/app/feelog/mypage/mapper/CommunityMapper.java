// 2025.04.26 조승찬

package com.app.feelog.mypage.mapper;

import com.app.feelog.domain.vo.CommunityPostFileVO;
import com.app.feelog.domain.vo.CommunityPostVO;
import com.app.feelog.domain.vo.PostVO;
import com.app.feelog.util.SixRowPagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommunityMapper {

    // 2025.04.26 조승찬 :: 페이지네이션을 위한 개인채널 커뮤니티 글 전체 건수
    int getCommunityPostListTotalCount(String channelUrl);

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 목록
    List<CommunityPostVO> getCommunityPostList(String channelUrl, SixRowPagination pagination);

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 좋아요 수
    int getLikeCount(Long postId);

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 댓글 수
    int getReplyCount(Long postId);

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 첨부 파일 목록
    List<CommunityPostFileVO> getCommunityPostFiles(Long postId);

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글에 내가 좋아요 했는지 여부
    boolean getILike(Long memberId, Long postId);

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 슈퍼키 테이블에 저장
    void postPost(CommunityPostVO postVO);

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 저장
    void postCommunityPost(@Param("channelUrl") String channelUrl, CommunityPostVO postVO);

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 파일 슈퍼키 테이블 저장
    void postFile(CommunityPostFileVO file);

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 파일 저장
    void postCommunityPostFile(CommunityPostFileVO file, @Param("postId") Long postId);

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글 가져오기
    Optional<CommunityPostVO> getCommunityPost(Long postId);

    // 2025.04.26 조승찬 ::  포스트 내용 수정
    void updateCommunityPost(CommunityPostVO postVO);

    // 2025.04.26 조승찬 ::  포스트 첨부 파일 삭제 (실제 삭제)
    void deleteCommunityPostFile(Long postId);

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 삭제
    void deleteCommunityPost(Long id);

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 첨부파일 삭제
    void updateCommunityPostFile(Long postId);

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 댓글 삭제
    void deleteCommunityPostReply(Long postId);

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 좋아요 삭제
    void deleteCommunityPostLike(Long postId);

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 신고 삭제
    void deleteCommunityPostReport(Long postId);

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 댓글 좋아요 삭제
    void deleteCommunityPostReplyLike(Long postId);

    // 2025.04.27  조승찬 :: 커뮤니티 게시글 댓글 신고 삭제
    void deleteCommunityPostReplyReport(Long postId);
}
