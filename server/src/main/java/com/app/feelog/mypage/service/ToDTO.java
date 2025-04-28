package com.app.feelog.mypage.service;

import com.app.feelog.domain.dto.*;
import com.app.feelog.domain.vo.*;
import com.app.feelog.mypage.dto.*;

import java.util.List;

public interface ToDTO {

    public default MemberDTO toMemberDTO(MemberVO memberVO) {
        MemberDTO memberDTO = null;
        if (memberVO != null) {
            memberDTO = new MemberDTO();
            memberDTO.setId(memberVO.getId());
            memberDTO.setMemberEmail(memberVO.getMemberEmail());
            memberDTO.setMemberPassword(memberVO.getMemberPassword());
            memberDTO.setMemberNickname(memberVO.getMemberNickname());
            memberDTO.setMemberIntroduce(memberVO.getMemberIntroduce());
            memberDTO.setMemberFilePath(memberVO.getMemberFilePath());
            memberDTO.setMemberFileName(memberVO.getMemberFileName());
            memberDTO.setMemberType(memberVO.getMemberType());
            memberDTO.setMemberNotificationPostReply(memberVO.getMemberNotificationPostReply());
            memberDTO.setMemberNotificationPostReplyLike(memberVO.getMemberNotificationPostReplyLike());
            memberDTO.setMemberNotificationPostLike(memberVO.getMemberNotificationPostLike());
            memberDTO.setMemberNotificationSubscribe(memberVO.getMemberNotificationSubscribe());
            memberDTO.setMemberNotificationCommunityPost(memberVO.getMemberNotificationCommunityPost());
            memberDTO.setMemberNotificationMessage(memberVO.getMemberNotificationMessage());
            memberDTO.setMemberStatus(memberVO.getMemberStatus());
            memberDTO.setCreatedDate(memberVO.getCreatedDate());
            memberDTO.setUpdatedDate(memberVO.getUpdatedDate());
        }
        return memberDTO;
    }

    public default MemberTaskDTO toMemberTaskDTO(MemberTaskVO memberTaskVO) {
        MemberTaskDTO memberTaskDTO = null;
        if (memberTaskVO != null) {
            memberTaskDTO = new MemberTaskDTO();
            memberTaskDTO.setId(memberTaskVO.getId());
            memberTaskDTO.setTaskPoolId(memberTaskVO.getTaskPoolId());
            memberTaskDTO.setMemberTaskStatus(memberTaskVO.getMemberTaskStatus());
            memberTaskDTO.setCreatedDate(memberTaskVO.getCreatedDate());
            memberTaskDTO.setUpdatedDate(memberTaskVO.getUpdatedDate());
        }
        return memberTaskDTO;
    }

    public default CommonTaskDTO toCommonTaskDTO(CommonTaskVO commonTaskVO) {
        CommonTaskDTO commonTaskDTO = null;
        if (commonTaskVO != null) {
            commonTaskDTO = new CommonTaskDTO();
            commonTaskDTO.setId(commonTaskVO.getId());
            commonTaskDTO.setCommonTaskName(commonTaskVO.getCommonTaskName());
            commonTaskDTO.setCommonTaskImg(commonTaskVO.getCommonTaskImg());
            commonTaskDTO.setCommonTaskTell(commonTaskVO.getCommonTaskTell());
            commonTaskDTO.setCommonTaskUrl(commonTaskVO.getCommonTaskUrl());
            commonTaskDTO.setCommonTaskAddr(commonTaskVO.getCommonTaskAddr());
            commonTaskDTO.setCommonTaskLot(commonTaskVO.getCommonTaskLot());
            commonTaskDTO.setCommonTaskLat(commonTaskVO.getCommonTaskLat());
            commonTaskDTO.setCommonTaskServiceName(commonTaskVO.getCommonTaskServiceName());
            commonTaskDTO.setCommonTaskReqPage(commonTaskVO.getCommonTaskReqPage());
            commonTaskDTO.setCreatedDate(commonTaskVO.getCreatedDate());
            commonTaskDTO.setUpdatedDate(commonTaskVO.getUpdatedDate());
        }
        return commonTaskDTO;
    }

    public default MemberTaskPoolDTO toMemberTaskPoolDTO(MemberTaskPoolVO memberTaskPoolVO) {
        MemberTaskPoolDTO memberTaskPoolDTO = null;
        if (memberTaskPoolVO != null) {
            memberTaskPoolDTO = new MemberTaskPoolDTO();
            memberTaskPoolDTO.setId(memberTaskPoolVO.getId());
            memberTaskPoolDTO.setMemberTaskPoolContent(memberTaskPoolVO.getMemberTaskPoolContent());
            memberTaskPoolDTO.setMemberTaskPoolFilePath(memberTaskPoolVO.getMemberTaskPoolFilePath());
            memberTaskPoolDTO.setMemberTaskPoolFileName(memberTaskPoolVO.getMemberTaskPoolFileName());
            memberTaskPoolDTO.setMemberTaskPoolStatus(memberTaskPoolVO.getMemberTaskPoolStatus());
            memberTaskPoolDTO.setCreatedDate(memberTaskPoolVO.getCreatedDate());
            memberTaskPoolDTO.setUpdatedDate(memberTaskPoolVO.getUpdatedDate());
        }
        return memberTaskPoolDTO;
    }

    public default MemberTaskPoolChallengeDTO toMemberTaskPoolChallengeDTO(MemberChallengeVO memberChallengeVO, MemberTaskPoolVO memberTaskPoolVO) {
        MemberTaskPoolChallengeDTO dto = new MemberTaskPoolChallengeDTO();

        if (memberChallengeVO != null) {
            dto.setId(memberChallengeVO.getId());
            dto.setMemberId(memberChallengeVO.getMemberId());
            dto.setChallengeComplete(memberChallengeVO.getChallengeComplete());
            dto.setChallengeStatus(memberChallengeVO.getChallengeStatus());
            dto.setCreatedDate(memberChallengeVO.getCreatedDate());
            dto.setUpdatedDate(memberChallengeVO.getUpdatedDate());
        }

        if (memberTaskPoolVO != null) {
            dto.setTaskId(memberTaskPoolVO.getId());
            dto.setMemberTaskPoolContent(memberTaskPoolVO.getMemberTaskPoolContent());
            dto.setMemberTaskPoolFilePath(memberTaskPoolVO.getMemberTaskPoolFilePath());
            dto.setMemberTaskPoolFileName(memberTaskPoolVO.getMemberTaskPoolFileName());
            dto.setMemberTaskPoolStatus(memberTaskPoolVO.getMemberTaskPoolStatus());
        }
        return dto;
    }

    public default CommonTaskChallengeDTO toCommonTaskChallengeDTO(CommonChallengeVO commonChallengeVO, CommonTaskVO commonTaskVO) {
        CommonTaskChallengeDTO dto = new CommonTaskChallengeDTO();

        if (commonChallengeVO != null) {
            dto.setId(commonChallengeVO.getId());
            dto.setMemberId(commonChallengeVO.getMemberId());
            dto.setChallengeComplete(commonChallengeVO.getChallengeComplete());
            dto.setChallengeStatus(commonChallengeVO.getChallengeStatus());
            dto.setCreatedDate(commonChallengeVO.getCreatedDate());
            dto.setUpdatedDate(commonChallengeVO.getUpdatedDate());
        }
        if (commonTaskVO != null) {
            dto.setTaskId(commonTaskVO.getId());
            dto.setCommonTaskName(commonTaskVO.getCommonTaskName());
            dto.setCommonTaskImg(commonTaskVO.getCommonTaskImg());
            dto.setCommonTaskTell(commonTaskVO.getCommonTaskTell());
            dto.setCommonTaskUrl(commonTaskVO.getCommonTaskUrl());
            dto.setCommonTaskAddr(commonTaskVO.getCommonTaskAddr());
            dto.setCommonTaskLot(commonTaskVO.getCommonTaskLot());
            dto.setCommonTaskLat(commonTaskVO.getCommonTaskLat());
            dto.setCommonTaskServiceName(commonTaskVO.getCommonTaskServiceName());
            dto.setCommonTaskReqPage(commonTaskVO.getCommonTaskReqPage());
            dto.setCommonTaskStatus(commonTaskVO.getCommonTaskStatus());
        }

        return dto;
    }

    public default AllChallengeListDTO toAllChallengeListDTO(CommonChallengeVO commonChallengeVO, MemberTaskPoolVO memberTaskPoolVO, CommonTaskVO commonTaskVO) {
        AllChallengeListDTO dto = new AllChallengeListDTO();

        if (commonChallengeVO != null) {
            dto.setId(commonChallengeVO.getId());
            dto.setMemberId(commonChallengeVO.getMemberId());
            dto.setTaskId(commonChallengeVO.getTaskId());
            dto.setChallengeComplete(commonChallengeVO.getChallengeComplete());
            dto.setCreatedDate(commonChallengeVO.getCreatedDate());
            dto.setUpdatedDate(commonChallengeVO.getUpdatedDate());
        }

        if (memberTaskPoolVO != null) {
            dto.setType("개별");
            dto.setMemberTaskPoolContent(memberTaskPoolVO.getMemberTaskPoolContent());
            dto.setMemberTaskPoolFilePath(memberTaskPoolVO.getMemberTaskPoolFilePath());
            dto.setMemberTaskPoolFileName(memberTaskPoolVO.getMemberTaskPoolFileName());
        }

        if (commonTaskVO != null) {
            dto.setType("공통");
            dto.setCommonTaskName(commonTaskVO.getCommonTaskName());
            dto.setCommonTaskImg(commonTaskVO.getCommonTaskImg());
            dto.setCommonTaskTell(commonTaskVO.getCommonTaskTell());
            dto.setCommonTaskUrl(commonTaskVO.getCommonTaskUrl());
            dto.setCommonTaskAddr(commonTaskVO.getCommonTaskAddr());
            dto.setCommonTaskLot(commonTaskVO.getCommonTaskLot());
            dto.setCommonTaskLat(commonTaskVO.getCommonTaskLat());
        }

        return dto;
    }

    public default ChannelDTO toChannelDTO(ChannelVO channelVO) {
        ChannelDTO channelDTO = null;
        if (channelVO != null) {
            channelDTO = new ChannelDTO();
            channelDTO.setId(channelVO.getId());
            channelDTO.setChannelTitle(channelVO.getChannelTitle());
            channelDTO.setChannelIntroduce(channelVO.getChannelIntroduce());
            channelDTO.setChannelUrl(channelVO.getChannelUrl());
            channelDTO.setChannelFilePath(channelVO.getChannelFilePath());
            channelDTO.setChannelFileName(channelVO.getChannelFileName());
            channelDTO.setChannelFileSize(channelVO.getChannelFileSize());
            channelDTO.setMemberId(channelVO.getMemberId());
            channelDTO.setChannelStatus(channelVO.getChannelStatus());
            channelDTO.setCreatedDate(channelVO.getCreatedDate());
            channelDTO.setUpdatedDate(channelVO.getUpdatedDate());
        }
        return channelDTO;
    }

    public default NotifyCommunityListDTO toNotifyCommunityListDTO(CommunityPostVO communityPostVO, MemberVO memberVO, ChannelVO channelVO, String timeAgo) {
        NotifyCommunityListDTO notifyCommunityListDTO = null;

        if (communityPostVO != null && memberVO != null) {
            notifyCommunityListDTO = new NotifyCommunityListDTO();
            notifyCommunityListDTO.setId(communityPostVO.getId());
            notifyCommunityListDTO.setPostTitle(communityPostVO.getPostTitle());
            notifyCommunityListDTO.setPostContent(communityPostVO.getPostContent());
            notifyCommunityListDTO.setMemberNickname(memberVO.getMemberNickname());
            notifyCommunityListDTO.setMemberFilePath(memberVO.getMemberFilePath());
            notifyCommunityListDTO.setMemberFileName(memberVO.getMemberFileName());
            notifyCommunityListDTO.setMemberId(communityPostVO.getMemberId());
            notifyCommunityListDTO.setChannelId(communityPostVO.getChannelId());
            notifyCommunityListDTO.setTimeAgo(timeAgo);
            notifyCommunityListDTO.setCreatedDate(communityPostVO.getCreatedDate());
            notifyCommunityListDTO.setUpdatedDate(communityPostVO.getUpdatedDate());
        }

        if (channelVO != null) {
            notifyCommunityListDTO.setMemberChannelId(channelVO.getId());
        }

        return notifyCommunityListDTO;
    }


    public default NotifyReplyListDTO toNotifyReplyListDTO(ChannelPostReplyVO channelPostReplyVO, MemberVO memberVO, ChannelVO channelVO, String timeAgo, String postTitle) {
        NotifyReplyListDTO notifyReplyListDTO = null;

        if (channelPostReplyVO != null && memberVO != null) {
            notifyReplyListDTO = new NotifyReplyListDTO();
            notifyReplyListDTO.setId(channelPostReplyVO.getId());
            notifyReplyListDTO.setReplyContent(channelPostReplyVO.getReplyContent());
            notifyReplyListDTO.setMemberNickname(memberVO.getMemberNickname());
            notifyReplyListDTO.setMemberFilePath(memberVO.getMemberFilePath());
            notifyReplyListDTO.setMemberFileName(memberVO.getMemberFileName());
            notifyReplyListDTO.setMemberId(channelPostReplyVO.getMemberId());
            notifyReplyListDTO.setPostId(channelPostReplyVO.getPostId());
            notifyReplyListDTO.setTimeAgo(timeAgo);
            notifyReplyListDTO.setPostTitle(postTitle);
            notifyReplyListDTO.setCreatedDate(channelPostReplyVO.getCreatedDate());
            notifyReplyListDTO.setUpdatedDate(channelPostReplyVO.getUpdatedDate());
        }

        if (channelVO != null) {
            notifyReplyListDTO.setMemberChannelId(channelVO.getId());
        }

        return notifyReplyListDTO;
    }


//    public default NotifyAdminListDTO toNotifyAdminListDTO(NoticeVO noticeVO, String timeAgo) {
//        NotifyAdminListDTO notifyAdminListDTO = null;
//        if (noticeVO != null) {
//            notifyAdminListDTO = new NotifyAdminListDTO();
//            notifyAdminListDTO.setId(noticeVO.getId());
//            notifyAdminListDTO.setNoticeTitle(noticeVO.getNoticeTitle());
//            notifyAdminListDTO.setNoticeContent(noticeVO.getNoticeContent());
//            notifyAdminListDTO.setMemberId(noticeVO.getMemberId());
//            notifyAdminListDTO.setNoticeFilePath(noticeVO.getNoticeFilePath());
//            notifyAdminListDTO.setNoticeFileName(noticeVO.getNoticeFileName());
//            notifyAdminListDTO.setCreatedDate(noticeVO.getCreatedDate());
//            notifyAdminListDTO.setUpdatedDate(noticeVO.getUpdatedDate());
//            notifyAdminListDTO.setTimeAgo(timeAgo);
//        }
//        return notifyAdminListDTO;
//    }

    public default NotifySubscribeListDTO toNotifySubscribeListDTO(ChannelVO channelVO, MemberVO memberVO) {
        NotifySubscribeListDTO dto = null;
        if (channelVO != null && memberVO != null) {
            dto = new NotifySubscribeListDTO();
            dto.setId(channelVO.getId());
            dto.setChannelTitle(channelVO.getChannelTitle());
            dto.setChannelIntroduce(channelVO.getChannelIntroduce());
            dto.setChannelUrl(channelVO.getChannelUrl());
            dto.setChannelFilePath(channelVO.getChannelFilePath());
            dto.setChannelFileName(channelVO.getChannelFileName());
            dto.setMemberId(channelVO.getMemberId());
            dto.setMemberNickname(memberVO.getMemberNickname());
            dto.setMemberFilePath(memberVO.getMemberFilePath());
            dto.setMemberFileName(memberVO.getMemberFileName());
            dto.setCreatedDate(channelVO.getCreatedDate());
            dto.setUpdatedDate(channelVO.getUpdatedDate());
        }
        return dto;
    }

    public default StorageSubscribeListDTO toStorageSubscribeListDTO(ChannelVO channelVO, MemberVO memberVO) {
        StorageSubscribeListDTO dto = null;
        if (channelVO != null && memberVO != null) {
            dto = new StorageSubscribeListDTO();
            dto.setId(channelVO.getId());
            dto.setChannelTitle(channelVO.getChannelTitle());
            dto.setChannelIntroduce(channelVO.getChannelIntroduce());
            dto.setChannelUrl(channelVO.getChannelUrl());
            dto.setChannelFilePath(channelVO.getChannelFilePath());
            dto.setChannelFileName(channelVO.getChannelFileName());
            dto.setMemberId(channelVO.getMemberId());
            dto.setMemberNickname(memberVO.getMemberNickname());
            dto.setMemberFilePath(memberVO.getMemberFilePath());
            dto.setMemberFileName(memberVO.getMemberFileName());
            dto.setCreatedDate(channelVO.getCreatedDate());
            dto.setUpdatedDate(channelVO.getUpdatedDate());
        }
        return dto;
    }

    public default StorageScrapListDTO toStorageScrapListDTO(ChannelPostScrapVO scrapVO, MemberVO memberVO, ChannelPostVO channelPostVO,
                                                             String timeAgo, int replyCount, int likeCount) {
        StorageScrapListDTO dto = null;
        if (scrapVO != null && memberVO != null && channelPostVO != null) {
            dto = new StorageScrapListDTO();
            dto.setId(scrapVO.getId());
            dto.setMemberId(scrapVO.getMemberId());
            dto.setMemberNickname(memberVO.getMemberNickname());
            dto.setMemberFilePath(memberVO.getMemberFilePath());
            dto.setMemberFileName(memberVO.getMemberFileName());
            dto.setPostId(scrapVO.getPostId());
            dto.setPostTitle(channelPostVO.getPostTitle());
            dto.setPostContent(channelPostVO.getPostContent());
            dto.setPostFilePath(channelPostVO.getPostFilePath());
            dto.setPostFileName(channelPostVO.getPostFileName());
            dto.setPostReadCount(channelPostVO.getPostReadCount());
            dto.setPostReplyCount(replyCount);
            dto.setPostLikeCount(likeCount);
            dto.setChannelId(channelPostVO.getChannelId());
            dto.setCreatedDate(channelPostVO.getCreatedDate());
            dto.setUpdatedDate(channelPostVO.getUpdatedDate());
            dto.setTimeAgo(timeAgo);
        }
        return dto;
    }

    public default StorageLikeListDTO toStorageLikeListDTO(ChannelPostLikeVO channelPostLikeVO, MemberVO memberVO, ChannelPostVO channelPostVO,
                                                             String timeAgo, int replyCount, int likeCount) {
        StorageLikeListDTO dto = null;
        if (channelPostLikeVO != null && memberVO != null && channelPostVO != null) {
            dto = new StorageLikeListDTO();
            dto.setId(channelPostLikeVO.getId());
            dto.setMemberId(channelPostLikeVO.getMemberId());
            dto.setMemberNickname(memberVO.getMemberNickname());
            dto.setMemberFilePath(memberVO.getMemberFilePath());
            dto.setMemberFileName(memberVO.getMemberFileName());
            dto.setPostId(channelPostLikeVO.getPostId());
            dto.setPostTitle(channelPostVO.getPostTitle());
            dto.setPostContent(channelPostVO.getPostContent());
            dto.setPostFilePath(channelPostVO.getPostFilePath());
            dto.setPostFileName(channelPostVO.getPostFileName());
            dto.setPostReadCount(channelPostVO.getPostReadCount());
            dto.setPostReplyCount(replyCount);
            dto.setPostLikeCount(likeCount);
            dto.setChannelId(channelPostVO.getChannelId());
            dto.setCreatedDate(channelPostVO.getCreatedDate());
            dto.setUpdatedDate(channelPostVO.getUpdatedDate());
            dto.setTimeAgo(timeAgo);
        }
        return dto;
    }

    public default StorageReplyListDTO toStorageReplyListDTO(ChannelPostReplyVO channelPostReplyVO, ChannelPostVO channelPostVO,
                                                             String timeAgo) {
        StorageReplyListDTO dto = null;
        if (channelPostReplyVO != null && channelPostVO != null) {
            dto = new StorageReplyListDTO();
            dto.setId(channelPostReplyVO.getId());
            dto.setReplyContent(channelPostReplyVO.getReplyContent());
            dto.setPostId(channelPostReplyVO.getPostId());
            dto.setPostTitle(channelPostVO.getPostTitle());
            dto.setPostContent(channelPostVO.getPostContent());
            dto.setPostFilePath(channelPostVO.getPostFilePath());
            dto.setPostFileName(channelPostVO.getPostFileName());
            dto.setCreatedDate(channelPostReplyVO.getCreatedDate());
            dto.setUpdatedDate(channelPostReplyVO.getUpdatedDate());
            dto.setTimeAgo(timeAgo);
        }
        return dto;
    }

    public default CommunityPostListDTO toCommunityPostListDTO(CommunityPostVO communityPostVO, MemberVO memberVO, List<CommunityPostFileVO> files,
                                                               ChannelVO channelVO, String timeAgo, int replyCount, int likeCount, int reportCount, boolean iLike, boolean iReport) {
        CommunityPostListDTO dto = null;
        if (communityPostVO != null && memberVO != null) {
            dto = new CommunityPostListDTO();
            dto.setId(communityPostVO.getId());
            dto.setMemberId(communityPostVO.getMemberId());
            dto.setPostContent(communityPostVO.getPostContent());
            dto.setMemberNickname(memberVO.getMemberNickname());
            dto.setMemberFilePath(memberVO.getMemberFilePath());
            dto.setMemberFileName(memberVO.getMemberFileName());
            dto.setLikeCount(likeCount);
            dto.setReplyCount(replyCount);
            dto.setReportCount(reportCount);
            dto.setILike(iLike);
            dto.setIReport(iReport);
            dto.setCreatedDate(communityPostVO.getCreatedDate());
            dto.setUpdatedDate(communityPostVO.getUpdatedDate());
            dto.setTimeAgo(timeAgo);
        }

        if (files != null && files.size() > 0) {
            dto.setFiles(files);
        }
        if (channelVO != null) {
            dto.setChannelId(channelVO.getId());
            dto.setChannelUrl(channelVO.getChannelUrl());
        }
        return dto;
    }

    public default CommunityPostReplyListDTO toCommunityPostReplyListDTO
            (CommunityPostVO communityPostVO, MemberVO memberVO, List<CommunityPostFileVO> files,
             ChannelVO channelVO, String timeAgo, int replyCount, int likeCount, int reportCount,
             boolean iLike, boolean iReport,
             List<CommunityPostReplyDetailDTO> replies) {

        CommunityPostReplyListDTO dto = null;
        if (communityPostVO != null && memberVO != null) {
            dto = new CommunityPostReplyListDTO();
            dto.setId(communityPostVO.getId());
            dto.setMemberId(communityPostVO.getMemberId());
            dto.setPostContent(communityPostVO.getPostContent());
            dto.setMemberNickname(memberVO.getMemberNickname());
            dto.setMemberFilePath(memberVO.getMemberFilePath());
            dto.setMemberFileName(memberVO.getMemberFileName());
            dto.setLikeCount(likeCount);
            dto.setReplyCount(replyCount);
            dto.setReportCount(reportCount);
            dto.setILike(iLike);
            dto.setIReport(iReport);
            dto.setCreatedDate(communityPostVO.getCreatedDate());
            dto.setUpdatedDate(communityPostVO.getUpdatedDate());
            dto.setTimeAgo(timeAgo);
        }

        if (files != null && files.size() > 0) {
            dto.setFiles(files);
        }

        if (channelVO != null) {
            dto.setChannelId(channelVO.getId());
            dto.setChannelUrl(channelVO.getChannelUrl());
        }

        if (replies != null && replies.size() > 0) {
            dto.setReplies(replies);
        }

        return dto;
    }


    public default CommunityPostReplyDetailDTO toCommunityPostReplyDetailDTO
            (CommunityPostReplyVO communityPostReplyVO, MemberVO memberVO,
             ChannelVO channelVO, String timeAgo, int likeCount, int reportCount,
             boolean iLike, boolean iReport) {

        CommunityPostReplyDetailDTO dto = null;
        if (communityPostReplyVO != null && memberVO != null) {
            dto = new CommunityPostReplyDetailDTO();
            dto.setId(communityPostReplyVO.getId());
            dto.setReplyContent(communityPostReplyVO.getReplyContent());
            dto.setCommunity_post_reply_file_path(communityPostReplyVO.getCommunity_post_reply_file_path());
            dto.setCommunity_post_reply_file_name(communityPostReplyVO.getCommunity_post_reply_file_name());
            dto.setCreatedDate(communityPostReplyVO.getCreatedDate());
            dto.setUpdatedDate(communityPostReplyVO.getUpdatedDate());
            dto.setTimeAgo(timeAgo);
            dto.setMemberId(communityPostReplyVO.getMemberId());
            dto.setPostId(communityPostReplyVO.getPostId());
            dto.setMemberNickname(memberVO.getMemberNickname());
            dto.setMemberFilePath(memberVO.getMemberFilePath());
            dto.setMemberFileName(memberVO.getMemberFileName());
            dto.setLikeCount(likeCount);
            dto.setReportCount(reportCount);
            dto.setILike(iLike);
            dto.setIReport(iReport);
        }

        if (channelVO != null) {
            dto.setChannelId(channelVO.getId());
            dto.setChannelUrl(channelVO.getChannelUrl());
        }

        return dto;
    }

}
