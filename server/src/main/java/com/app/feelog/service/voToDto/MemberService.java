package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.NoticeDTO;
import com.app.feelog.domain.dto.NoticeListDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.domain.vo.NoticeVO;
import com.app.feelog.util.pagination.MemberPagination;
import com.app.feelog.util.pagination.NoticePagination;

import java.util.List;

public interface MemberService {
//    공지 목록 조회
    public List<MemberDTO> getMemberList(MemberPagination memberPagination);

    public default MemberDTO toMemberDTO(MemberVO memberVO) {
        MemberDTO memberDTO = new MemberDTO();
        if(memberVO != null) {
            memberDTO.setId(memberVO.getId());
            memberDTO.setMemberEmail(memberVO.getMemberEmail());
            memberDTO.setMemberNickname(memberVO.getMemberNickname());
            memberDTO.setMemberStatus(memberVO.getMemberStatus());
            memberDTO.setCreatedDate(memberVO.getCreatedDate());
            memberDTO.setUpdatedDate(memberVO.getUpdatedDate());
        }
        return memberDTO;
    }
}
