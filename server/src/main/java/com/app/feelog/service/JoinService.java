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
public class JoinService {
    private final MemberDAO memberDAO;

    public Optional<MemberDTO> getMemberByEmail(String memberEmail) {
        return memberDAO.getMemberByEmail(memberEmail);
    }

    public void kakaoJoin(MemberDTO memberDTO) {
        memberDAO.kakaoJoin(memberDTO);
    }
}
