// 2025.04.18 조승찬
package com.app.feelog.mypage.service;

import com.app.feelog.mypage.repository.JoinLogDAO;
import com.app.feelog.domain.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService implements ToDTO{
    private final JoinLogDAO joinLogDAO;

    // 2025.04.18 조승찬 :: 이메일번호와 비번으로 멤버 확인
    public Optional<MemberDTO> getMemberByEmailAndPassword(MemberDTO memberDTO) {

        return Optional.ofNullable(
                toMemberDTO(joinLogDAO.getMemberByEmailAndPassword(memberDTO.toVO())
                        .orElse(null)));
    }

    // 2025.04.19 조승찬 :: 이메일번호로 멤버 확인
    public Optional<MemberDTO> getMemberByEmail(String email) {

        return Optional.ofNullable(
                toMemberDTO(joinLogDAO.getMemberByEmail(email)
                        .orElse(null)));
    }

}
