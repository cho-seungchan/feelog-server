// 2025.04.21 조승찬
package com.app.feelog.mypage.mapper;

import com.app.feelog.domain.vo.*;
import com.app.feelog.util.Pagination;
import com.app.feelog.util.SixRowPagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MyPageMapper {

    // 2025.04.21  조승찬 :: 프로필 수정
    void postSettingProfile(MemberVO memberVOvo);

    // 2025.04.21 조승찬 :: 아이디로 멤버 정보 가져오기
    Optional<MemberVO> getMemberById(Long id);

    // 2025.04.22  조승찬 :: 알림정보 수정
    void postSettingNotify(MemberVO memberVO);

    // 2025.04.22 조승찬 :: url로 채널 정보 가져오기
    Optional<ChannelVO> getChannelByUrl(String channelUrl);

    // 2025.04.22 조승찬 :: 채널 생성하기
    void postMakingChannel(ChannelVO channelVO);

    // 2025.04.23 조승찬 :: 채널 수정하기
    void postUpdateChannel(ChannelVO channelVO);

    // 2025.04.23 조승찬 :: 채널 삭제하기
    void postDeleteChannel(Long id);

    // 2025.04.23 조승찬 :: 닉네임 중복체크
    Optional<MemberVO> getMemberByNickname(String memberNickname);

    // 2025.04.23 조승찬 :: 페이지 네이션을 위해 커뮤니티 포스트 총 건수 가져오기
    int getNotifyCommunityListTotalCount(Long memberId);

    // 2025.04.23 조승찬 :: 채널의 커뮤니티 게시글 가져오기
    List<CommunityPostVO> getNotifyCommunityList(@Param("memberId") Long memberId, @Param("pagination") SixRowPagination pagination);

    // 2025.04.23 조승찬 :: 채널의 커뮤니티 게시글 관련 파일 가져오기
    List<CommunityPostVO> getCommunityPostFileList(Long communityPostId);

    // 2025.04.23 조승찬 :: 멤버 아이디로 채널 정보 가져오기
    Optional<ChannelVO> getChannelByMemberId(Long memberId);

    // 2025.04.23 조승찬 :: 페이지 네이션을 위해 포스트 댓글 총 건수 가져오기
    int getNotifyReplyListTotalCount(Long memberId);

    // 2025.04.23 조승찬 :: 포스트의 댓글 가져오기
    List<ChannelPostReplyVO> getNotifyReplyList(Long memberId, SixRowPagination pagination);

    // 2025.04.23 조승찬 :: 포스트 정보 가져오기
    Optional<PostVO> getPostById(Long id);

    // 2025.04.24 조승찬 :: 페이지 네이션을 위해 관리자 공지 전체 건수 가져오기
    int getNotifyAdminListTotalCount();

    // 2025.04.24 조승찬 :: 알림 메뉴 중 관리자 공지 목록을 위해 일기 정보 가져오기
    Optional<DiaryVO> getDiaryByMemberId(Long memberId);

    // 2025.04.24 조승찬 :: 페이지 네이션을 위해 구독자 채널 총 건수 가져오기
    int getNotifySubscribeTotalCount(Long memberId);

    // 2025.04.24 조승찬 :: 구독자 채널 리스트 조회
    List<MemberVO> getNotifySubscribe(Long memberId, SixRowPagination pagination);

    // 2025.04.24 조승찬 :: 구독자 취소
    void cancelSubscribe(SubscribeVO subscribeVO);

    // 2025.04.24 조승찬 :: 페이지 네이션을 위해 구독 채널 총 건수 가져오기
    int getStorageSubscribeTotalCount(Long memberId);

    // 2025.04.24 조승찬 :: 구독 채널 리스트 조회
    List<ChannelVO> getStorageSubscribe(Long memberId, SixRowPagination pagination);

    // 2025.04.25 조승찬 :: 페이지 네이션을 위해 스크랩 총 건수 가져오기
    int getStorageScrapTotalCount(Long memberId);

    // 2025.04.25 조승찬 :: 스크랩 목록 가져오기
    List<ChannelPostScrapVO> getStorageScrap(Long memberId, SixRowPagination pagination);

    // 2025.04.25 조승찬 :: 스크랩한 포스크 정보 가져오기
    Optional<ChannelPostVO> getChannelPostById(Long id);

    // 2025.04.25 조승찬 포스트의 좋아요 건수 가져오기
    int getLikeCount(Long postId);

    // 2025.04.25 조승찬 포스트의 댓글 건수 가져오기
    int getReplyCount(Long postId);

    // 2025.04.25 스크랩 재설정하기
    void storageOnScrap(Long id);

    // 2025.04.25 스크랩 취소하기
    void storageOffScrap(Long id);

    // 2025.04.25 조승찬 :: 페이지 네이션을 위해 좋아요 총 건수 가져오기
    int getStorageLikeTotalCount(Long memberId);

    // 2025.04.25 조승찬 :: 좋아요 목록 가져오기
    List<ChannelPostLikeVO> getStorageLike(Long memberId, SixRowPagination pagination);

    // 2025.04.25 좋아요 재설정하기
    void storageOnLike(Long id);

    // 2025.04.25 좋아요 취소하기
    void storageOffLike(Long id);

    // 2025.04.25 조승찬 :: 페이지네이션을 위한 댓글 건수 가져오기
    int getStorageReplyTotalCount(Long memberId);

    // 2025.04.25 조승찬 :: 댓글 목록 가져오기
    List<ChannelPostReplyVO> getStorageReply(Long memberId, SixRowPagination pagination);

    // 2025.04.25 조승찬 :: 댓글 삭제
    void deleteStorageReply(Long id);
}
