package com.app.feelog.mapper;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    // 2025.04.17 조승찬 :: 아이디로 멤버 정보 가져오기
    Optional<MemberVO> getMemberByEmail(@Param("memberEmail") String memberEmail);

    // 2025.04.17 조승찬 :: 카카오 회원 가입시 멤버 이메일, 닉네임 저장
    void kakaoJoin(MemberVO memberVO);

    // 2025.04.18 조승찬 :: 닉네임으로 멤버 정보 가져오기
    Optional<MemberVO> getMemberByNickname(String memberNickname);

    // 2025.04.18 조승찬 :: 이메일 회원가입시 멤버 이메일, 닉네임, 비밀번호 저장
    void emailJoin(MemberVO memberVO);
}
