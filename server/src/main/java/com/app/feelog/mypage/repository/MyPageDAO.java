// 2025.04.21 조승찬
package com.app.feelog.mypage.repository;

import com.app.feelog.domain.vo.*;
import com.app.feelog.mypage.mapper.MyPageMapper;
import com.app.feelog.util.SixRowPagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MyPageDAO {
    private final MyPageMapper myPageMapper;

    // 2025.04.21  조승찬 :: 프로필 수정
    public void postSettingProfile(MemberVO memberVOvo) {
        myPageMapper.postSettingProfile(memberVOvo);
    }

    // 2025.04.21 조승찬 :: 아이디로 멤버 정보 가져오기
    public Optional<MemberVO> getMemberById(Long id) {
        return Optional.ofNullable(
                myPageMapper.getMemberById(id)
                        .orElse(null));
    }

    // 2025.04.22  조승찬 :: 알림정보 수정
    public void postSettingNotify(MemberVO memberVO) {
        myPageMapper.postSettingNotify(memberVO);
    }

    // 2025.04.22 조승찬 :: url로 채널 정보 가져오기
    public Optional<ChannelVO> getChannelByUrl(String channelUrl) {
        return Optional.ofNullable(
                myPageMapper.getChannelByUrl(channelUrl)
                        .orElse(null));
    }

    // 2025.04.22 조승찬 :: 채널 생성하기
    public void postMakingChannel(ChannelVO channelVO) {
        myPageMapper.postMakingChannel(channelVO);
    }

    // 2025.04.23 조승찬 :: 채널 수정하기
    public void postUpdateChannel(ChannelVO channelVO) {
        myPageMapper.postUpdateChannel(channelVO);
    }

    // 2025.04.23 조승찬 :: 채널 삭제하기
    public void postDeleteChannel(Long id) {
        myPageMapper.postDeleteChannel(id);
    }

    // 2025.04.23 조승찬 :: 닉네임 중복체크
    public Optional<MemberVO> getMemberByNickname(String memberNickname) {
        return Optional.ofNullable(
                myPageMapper.getMemberByNickname(memberNickname)
                        .orElse(null));
    }

    // 2025.04.23 조승찬 :: 페이지 네이션을 위해 커뮤니티 포스트 총 건수 가져오기
    public int getNotifyCommunityListTotalCount(Long memberId) {
        return myPageMapper.getNotifyCommunityListTotalCount(memberId);
    }

    // 2025.04.23 조승찬 :: 채널의 커뮤니티 게시글 가져오기
    public List<CommunityPostVO> getNotifyCommunityList(Long memberId, SixRowPagination pagination) {
        return myPageMapper.getNotifyCommunityList(memberId, pagination);
    }

    // 2025.04.23 조승찬 :: 채널의 커뮤니티 게시글에 등록된 파일 가져오기
    public List<CommunityPostVO> getCommunityPostFileList(Long communityPostId) {
        return myPageMapper.getCommunityPostFileList(communityPostId);
    };

    // 2025.04.23 조승찬 :: 멤버 아이디로 채널 정보 가져오기
    public Optional<ChannelVO> getChannelByMemberId(Long memberId) {
        return Optional.ofNullable(
                myPageMapper.getChannelByMemberId(memberId)
                        .orElse(null));
    };

    // 2025.04.23 조승찬 :: 페이지 네이션을 위해 포스트 댓글 총 건수 가져오기
    public int getNotifyReplyListTotalCount(Long memberId) {
        return myPageMapper.getNotifyReplyListTotalCount(memberId);
    }

    // 2025.04.23 조승찬 :: 포스트의 댓글 가져오기
    public List<ChannelPostReplyVO> getNotifyReplyList(Long memberId, SixRowPagination pagination) {
        return myPageMapper.getNotifyReplyList(memberId, pagination);
    }

    // 2025.04.23 조승찬 :: 포스트 정보 가져오기
    public Optional<PostVO> getPostById(Long id) {
        return Optional.ofNullable(
                myPageMapper.getPostById(id)
                        .orElse(null));
    }

    // 2025.04.24 조승찬 :: 페이지 네이션을 위해 관리자 공지 전체 건수 가져오기
    public int getNotifyAdminListTotalCount() {
        return myPageMapper.getNotifyAdminListTotalCount();
    }

    // 2025.04.24 조승찬 :: 알림 메뉴 중 관리자 공지 목록을 위해 일기 정보 가져오기
    public Optional<DiaryVO> getDiaryByMemberId(Long memberId) {
        return Optional.ofNullable(
                myPageMapper.getDiaryByMemberId(memberId)
                        .orElse(null));
    }

    // 2025.04.24 조승찬 :: 페이지 네이션을 위해 구독자 채널 총 건수 가져오기
    public int getNotifySubscribeTotalCount(Long memberId) {
        return myPageMapper.getNotifySubscribeTotalCount(memberId);
    }

    // 2025.04.24 조승찬 :: 구독자 채널 리스트 조회
    public List<MemberVO> getNotifySubscribe(Long memberId, SixRowPagination pagination) {
        return myPageMapper.getNotifySubscribe(memberId, pagination);
    }

    // 2025.04.24 조승찬 :: 구독자 취소
    public void cancelSubscribe(SubscribeVO subscribeVO) {
        myPageMapper.cancelSubscribe(subscribeVO);
    }

    // 2025.04.24 조승찬 :: 페이지 네이션을 위해 구독 채널 총 건수 가져오기
    public int getStorageSubscribeTotalCount(Long memberId) {
        return myPageMapper.getStorageSubscribeTotalCount(memberId);
    }

    // 2025.04.24 조승찬 :: 구독 채널 리스트 조회
    public List<ChannelVO> getStorageSubscribe(Long memberId, SixRowPagination pagination) {
        return myPageMapper.getStorageSubscribe(memberId, pagination);
    }

    // 2025.04.25 조승찬 :: 페이지 네이션을 위해 스크랩 총 건수 가져오기
    public int getStorageScrapTotalCount(Long memberId) {
        return myPageMapper.getStorageScrapTotalCount(memberId);
    }

    // 2025.04.25 조승찬 :: 스크랩 목록 가져오기
    public List<ChannelPostScrapVO> getStorageScrap(Long memberId, SixRowPagination pagination) {
        return myPageMapper.getStorageScrap(memberId, pagination);
    }

    // 2025.04.25 조승찬 :: 스크랩한 포스트 정보 가져오기
    public Optional<ChannelPostVO> getChannelPostById(Long id) {
        return Optional.ofNullable(
                myPageMapper.getChannelPostById(id)
                        .orElse(null));
    }

    // 2025.04.25 조승찬 포스트의 좋아요 건수 가져오기
    public int getLikeCount(Long postId) {
        return myPageMapper.getLikeCount(postId);
    }

    // 2025.04.25 조승찬 포스트의 댓글 건수 가져오기
    public int getReplyCount(Long postId) {
        return myPageMapper.getReplyCount(postId);
    }

    // 2025.04.25 스크랩 재설정하기
    public void storageOnScrap(Long id) {
        myPageMapper.storageOnScrap(id);
    }

    // 2025.04.25 스크랩 취소하기
    public void storageOffScrap(Long id) {
        myPageMapper.storageOffScrap(id);
    }

    // 2025.04.25 조승찬 :: 페이지 네이션을 위해 좋아요 총 건수 가져오기
    public int getStorageLikeTotalCount(Long memberId) {
        return myPageMapper.getStorageLikeTotalCount(memberId);
    }

    // 2025.04.25 조승찬 :: 좋아요 목록 가져오기
    public List<ChannelPostLikeVO> getStorageLike(Long memberId, SixRowPagination pagination) {
        return  myPageMapper.getStorageLike(memberId, pagination);
    }

    // 2025.04.25 조승찬 ::  좋아요 재설정하기
    public void storageOnLike(Long id) {
        myPageMapper.storageOnLike(id);
    }

    // 2025.04.25 조승찬 ::  좋아요 취소하기
    public void storageOffLike(Long id) {
        myPageMapper.storageOffLike(id);
    }

    // 2025.04.25 조승찬 :: 페이지네이션을 위한 댓글 건수 가져오기
    public int getStorageReplyTotalCount(Long memberId) {
        return myPageMapper.getStorageReplyTotalCount(memberId);
    }

    // 2025.04.25 조승찬 :: 댓글 목록 가져오기
    public List<ChannelPostReplyVO> getStorageReply(Long memberId, SixRowPagination pagination) {
        return myPageMapper.getStorageReply(memberId, pagination);
    }

    // 2025.04.25 조승찬 :: 댓글 삭제
    public void deleteStorageReply(Long id) {
        myPageMapper.deleteStorageReply(id);
    }
}

