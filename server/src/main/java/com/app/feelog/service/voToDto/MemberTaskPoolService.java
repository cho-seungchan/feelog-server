package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.MemberTaskPoolDTO;
import com.app.feelog.domain.vo.MemberTaskPoolVO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.util.Pagination;
import com.app.feelog.util.pagination.AdminPagination;
import com.app.feelog.util.pagination.MemberPagination;

import java.util.List;

public interface MemberTaskPoolService {
    public List<MemberTaskPoolDTO> getMemberTaskPoolAll(MemberPagination memberPagination);

//    VO 객체를 전달받으면
    public default MemberTaskPoolDTO toMemberTaskPoolDTO(MemberTaskPoolVO memberTaskPoolVO) {
        MemberTaskPoolDTO memberTaskPoolDTO = new MemberTaskPoolDTO();
//        전달받은 VO 객체가 null이 아닐때
        if(memberTaskPoolVO != null) {
//        VO의 필드값을 DTO의 필드값으로 변환시켜준다
            memberTaskPoolDTO.setId(memberTaskPoolVO.getId());
            memberTaskPoolDTO.setMemberTaskPoolContent(memberTaskPoolVO.getMemberTaskPoolContent());
            memberTaskPoolDTO.setMemberTaskPoolStatus(memberTaskPoolVO.getMemberTaskPoolStatus());
            memberTaskPoolDTO.setCreatedDate(memberTaskPoolVO.getCreatedDate());
            memberTaskPoolDTO.setMemberTaskPoolFileName(memberTaskPoolVO.getMemberTaskPoolFileName());
            memberTaskPoolDTO.setMemberTaskPoolFilePath(memberTaskPoolVO.getMemberTaskPoolFilePath());
        }
        return memberTaskPoolDTO;
    }
}
