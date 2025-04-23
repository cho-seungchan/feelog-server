package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.NoticeDTO;
import com.app.feelog.domain.dto.NoticeListDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.domain.vo.NoticeVO;
import com.app.feelog.util.pagination.AdminPagination;
import com.app.feelog.util.pagination.NoticePagination;

import java.util.List;

public interface NoticeService {
//    공지 목록 조회
    public List<NoticeListDTO> getNoticeList(NoticePagination noticePagination);

    public default NoticeDTO toNoticeDTO(NoticeVO noticeVO) {
        NoticeDTO noticeDTO = new NoticeDTO();
        if(noticeVO != null) {
            noticeDTO.setId(noticeVO.getId());
            noticeDTO.setNoticeTitle(noticeVO.getNoticeTitle());
            noticeDTO.setNoticeContent(noticeVO.getNoticeContent());
            noticeDTO.setNoticeStatus(noticeVO.getNoticeStatus());
            noticeDTO.setCreatedDate(noticeVO.getCreatedDate());
        }
        return noticeDTO;
    }

    public default MemberDTO toMemberDTO(MemberVO memberVO) {
        MemberDTO memberDTO = new MemberDTO();
        if(memberVO != null) {
            memberDTO.setMemberNickname(memberVO.getMemberNickname());
        }
        return memberDTO;
    }
}
