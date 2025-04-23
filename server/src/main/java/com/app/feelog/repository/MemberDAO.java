package com.app.feelog.repository;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.mapper.MemberMapper;
import com.app.feelog.util.pagination.MemberPagination;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

    // 2025.04.17 조승찬 :: 아이디로 멤버 정보 가져오기
    public Optional<MemberVO> getMemberByEmail(String memberEmail) {
        return memberMapper.getMemberByEmail(memberEmail);
    }

    // 2025.04.17 조승찬 :: 카카오 회원 가입시 멤버 이메일, 닉네임 저장
    public void kakaoJoin(MemberVO memberVO) {
        memberMapper.kakaoJoin(memberVO);
    }

    // 2025.04.18 조승찬 :: 닉네임으로 멤버 정보 가져오기
    public Optional<MemberVO> getMemberByNickname(String memberNickname) {
        return memberMapper.getMemberByNickname(memberNickname);
    }

    // 2025.04.18 조승찬 :: 이메일 회원가입시 멤버 이메일, 닉네임, 비밀번호 저장
    public void emailJoin(MemberVO  memberVO) {
        memberMapper.emailJoin(memberVO);
    }

    public Optional<MemberDTO> getMemberByEmailAndPassword(MemberVO vo) {
        return null;
    }

    //    박정근 :: 전체회원 조회
    public List<MemberVO> findMemberAll(MemberPagination memberPagination){
        return memberMapper.selectMemberAll(memberPagination);
    };

    //    박정근 :: 전체회원 갯수
    public int selectMemberCount(){
        return memberMapper.selectMemberCount();
    };

    //    박정근 :: 회원 탈퇴
    public void deleteMember(Long id){
        memberMapper.deleteMember(id);
    };
    //    박정근 :: 회원 정지
    public void bannedMember(Long id){
        memberMapper.bannedMember(id);
    };
    //    박정근 :: 정지 취소
    public void activeMember(Long id){
        memberMapper.activeMember(id);
    };

    //    박정근 :: 검색어에 포함되는 회원 조회
    public List<MemberVO> findSearchAll(@Param("pagination") MemberPagination memberPagination, @Param("keyword") String keyword){
        return memberMapper.selectSearchAll(memberPagination, keyword);
    };
    public int findSearchAllCount(String keyword){
        return memberMapper.selectSearchAllCount(keyword);
    };
}
