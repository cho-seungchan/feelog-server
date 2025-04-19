package com.app.feelog.mypage.service;

import com.app.feelog.domain.dto.CommonTaskDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.MemberTaskDTO;
import com.app.feelog.domain.dto.MemberTaskPoolDTO;
import com.app.feelog.domain.vo.*;

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
            memberDTO.setMemberFileSize(memberVO.getMemberFileSize());
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

}
