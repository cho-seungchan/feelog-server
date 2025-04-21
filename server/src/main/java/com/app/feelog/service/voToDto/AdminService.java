package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.util.pagination.AdminPagination;

import java.util.List;

public interface AdminService {
//    관리자 조회
    public List<MemberDTO> getAdminList(AdminPagination adminPagination);

    public default MemberDTO toMemberDTO(MemberVO memberVO) {
        MemberDTO memberDTO = new MemberDTO();
        if(memberVO != null) {
            memberDTO.setId(memberVO.getId());
            memberDTO.setMemberEmail(memberVO.getMemberEmail());
            memberDTO.setMemberNickname(memberVO.getMemberNickname());
            memberDTO.setMemberStatus(memberVO.getMemberStatus());
        }
        return memberDTO;
    }
}
