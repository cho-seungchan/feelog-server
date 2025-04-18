package com.app.feelog.repository;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
