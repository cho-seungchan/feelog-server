// 2025.04.26 조승찬

package com.app.feelog.mypage.repository;

import com.app.feelog.domain.vo.CommunityPostFileVO;
import com.app.feelog.domain.vo.CommunityPostVO;
import com.app.feelog.domain.vo.PostVO;
import com.app.feelog.mypage.mapper.CommunityMapper;
import com.app.feelog.util.SixRowPagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    // 2025.04.26 조승찬 :: 개인채널 커뮤니티 글에 내가 좋아요 했는지 여부
    public boolean getILike(Long memberId, Long postId) {
        return communityMapper.getILike(memberId, postId);
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
}
