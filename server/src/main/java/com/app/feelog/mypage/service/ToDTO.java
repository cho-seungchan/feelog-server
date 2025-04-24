package com.app.feelog.mypage.service;

import com.app.feelog.domain.dto.*;
import com.app.feelog.domain.vo.*;
import com.app.feelog.mypage.dto.*;

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

}
