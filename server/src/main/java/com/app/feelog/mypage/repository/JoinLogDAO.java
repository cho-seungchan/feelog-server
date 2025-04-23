package com.app.feelog.mypage.repository;

import com.app.feelog.mypage.mapper.JoinLogMapper;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JoinLogDAO {
    private final JoinLogMapper joinLogMapper;

    // 2025.04.17 조승찬 :: 아이디로 멤버 정보 가져오기
    public Optional<MemberVO> getMemberByEmail(String memberEmail) {

        return Optional.ofNullable(
                joinLogMapper.getMemberByEmail(memberEmail)
                        .orElse(null));
    }

    // 2025.04.17 조승찬 :: 카카오 회원 가입시 멤버 이메일, 닉네임 저장
    public void kakaoJoin(MemberVO memberVO) {
        joinLogMapper.kakaoJoin(memberVO);
    }

    // 2025.04.18 조승찬 :: 닉네임으로 멤버 정보 가져오기
    public Optional<MemberVO> getMemberByNickname(String memberNickname) {
        return Optional.ofNullable(
                joinLogMapper.getMemberByNickname(memberNickname)
                        .orElse(null));
    }

    // 2025.04.18 조승찬 :: 이메일 회원가입시 멤버 이메일, 닉네임, 비밀번호 저장
    public void emailJoin(MemberVO  memberVO) {
        joinLogMapper.emailJoin(memberVO);
    }

    // 2025.04.18 조승찬 :: 이메일과 비번으로 멤버 정보 가져오기
    public Optional<MemberVO> getMemberByEmailAndPassword(MemberVO memberVO) {
        return Optional.ofNullable(
                joinLogMapper.getMemberByEmailAndPassword(memberVO)
                        .orElse(null));
    }

}
