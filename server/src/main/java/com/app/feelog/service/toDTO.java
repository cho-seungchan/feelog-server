package com.app.feelog.service;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.domain.vo.Period;

public interface toDTO {

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
            // createdDate와 updatedDate는 VO에서 Period를 상속하므로 Period 내부 로직에 따라 설정이 필요
            if (memberVO instanceof Period) {
                Period period = (Period) memberVO;
                memberDTO.setCreatedDate(period.getCreatedDate());
                memberDTO.setUpdatedDate(period.getUpdatedDate());
            }
        }
        return memberDTO;
    }
}
