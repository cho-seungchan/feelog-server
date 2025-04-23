package com.app.feelog.service;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.MemberListDTO;
import com.app.feelog.domain.dto.MemberTaskPoolDTO;
import com.app.feelog.domain.dto.MemberTaskPoolListDTO;
import com.app.feelog.domain.vo.MemberTaskPoolVO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.repository.MemberDAO;
import com.app.feelog.repository.MemberTaskPoolDAO;
import com.app.feelog.service.voToDto.MemberService;
import com.app.feelog.service.voToDto.MemberTaskPoolService;
import com.app.feelog.util.Pagination;
import com.app.feelog.util.pagination.MemberPagination;
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
public class MemberTaskPoolServiceImpl implements MemberTaskPoolService {
    private final MemberTaskPoolDAO memberTaskPoolDAO;

    public void insertMemberTaskPool(MemberTaskPoolDTO memberTaskPoolDTO) {
        memberTaskPoolDAO.saveMemberTaskPool(memberTaskPoolDTO.toVO());
    }

//    DTO 변환
    @Override
    public List<MemberTaskPoolDTO> getMemberTaskPoolAll(MemberPagination memberPagination) {
//        기존 DAO에서 VO List로 받는 메소드 사용하여 새로운 VO리스트 객체에 담아논다
        List<MemberTaskPoolVO> memberTaskPoolVOList = memberTaskPoolDAO.findMemberTaskPoolAll(memberPagination);
//        DTO 리스트 객체 생성 후
        List<MemberTaskPoolDTO> memberTaskPoolDTOList =
//                VO 리스트 객체를 스트림으로 변환
                memberTaskPoolVOList.stream()
//                        스트림 내의 각 요소를 toMember 메서드를 사용하여 DTO로 변환
                        .map(this::toMemberTaskPoolDTO)
//                        변환된 요소들을 다시 리스트로 모아 반환한다.
                        .collect(Collectors.toList());
        return memberTaskPoolDTOList;
    }

    public MemberTaskPoolListDTO getMemberTaskPoolList(MemberPagination memberPagination) {
        MemberTaskPoolListDTO memberTaskPoolList = new MemberTaskPoolListDTO();

        memberPagination.create(memberTaskPoolDAO.findCountAll());

        memberTaskPoolList.setMemberPagination(memberPagination);
        memberTaskPoolList.setMemberTaskPoolList(getMemberTaskPoolAll(memberPagination));

        return memberTaskPoolList;
    }

    public void deleteMemberTaskPoolById(Long id){
        memberTaskPoolDAO.deleteMemberTaskPoolById(id);
    };

    public void updateMemberTaskPool(MemberTaskPoolDTO memberTaskPoolDTO) {
        memberTaskPoolDAO.setMemberTaskPool(memberTaskPoolDTO.toVO());
    };

}
