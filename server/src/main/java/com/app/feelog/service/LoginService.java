// 2025.04.18 조승찬
package com.app.feelog.service;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final MemberDAO memberDAO;

//    // 2025.04.18 조승찬 :: 이메일 로그인 멤버 확인
//    public Optional<MemberDTO> getMemberByEmailAndPassword(MemberDTO memberDTO) {
//        return memberDAO.getMemberByEmailAndPassword(memberDTO.toVO());
//    }
}
