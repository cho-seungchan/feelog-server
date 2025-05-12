package com.app.feelog.mypage.mapper;

import com.app.feelog.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface JoinLogMapper {

    // 2025.04.17 조승찬 :: 이메일로 멤버 정보 가져오기
    Optional<MemberVO> getMemberByEmail(String memberEmail);

    // 2025.04.17 조승찬 :: 카카오 회원 가입시 멤버 이메일, 닉네임 저장
    void kakaoJoin(MemberVO memberVO);

    // 2025.04.18 조승찬 :: 닉네임으로 멤버 정보 가져오기
    Optional<MemberVO> getMemberByNickname(String memberNickname);

    // 2025.04.18 조승찬 :: 이메일 회원가입시 멤버 이메일, 닉네임, 비밀번호 저장
    void emailJoin(MemberVO memberVO);

    // 2025.04.19 조승찬 :: 이메일과 비밀번호로 멤버 정보 가져오기
    Optional<MemberVO> getMemberByEmailAndPassword(MemberVO memberVO);

}
