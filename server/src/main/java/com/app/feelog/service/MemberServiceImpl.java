package com.app.feelog.service;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.MemberListDTO;
import com.app.feelog.domain.dto.NoticeDTO;
import com.app.feelog.domain.dto.NoticeListDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.repository.MemberDAO;
import com.app.feelog.repository.NoticeDAO;
import com.app.feelog.service.voToDto.MemberService;
import com.app.feelog.util.pagination.MemberPagination;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;

//    DTO로 전환
    @Override
    public List<MemberDTO> getMemberList(MemberPagination memberPagination) {
        List<MemberVO> memberVOList = memberDAO.findMemberAll(memberPagination);

        List<MemberDTO> memberDTOList =
                memberVOList
                        .stream()
                        .map(this::toMemberDTO)
                        .collect(Collectors.toList());

        return memberDTOList;
    }

    public MemberListDTO getMemberLists(MemberPagination memberPagination){
        MemberListDTO memberList = new MemberListDTO();

        memberPagination.create(memberDAO.selectMemberCount());

        memberList.setMemberPagination(memberPagination);
        memberList.setMemberList(getMemberList(memberPagination));

        return memberList;
    }

    public void deleteMember(Long id){
        memberDAO.deleteMember(id);
    };
    public void bannedMember(Long id){
        memberDAO.bannedMember(id);
    };
    public void activeMember(Long id){
        memberDAO.activeMember(id);
    };

    //    박정근 :: 검색어에 포함되는 회원 조회
    public MemberListDTO getSearchAll(@Param("pagination") MemberPagination memberPagination, @Param("keyword") String keyword){
        MemberListDTO memberList = new MemberListDTO();

        memberPagination.create(memberDAO.findSearchAllCount(keyword));
        memberList.setMemberPagination(memberPagination);
        memberList.setMemberList(getMemberList(memberPagination));

        return memberList;
    };

}
