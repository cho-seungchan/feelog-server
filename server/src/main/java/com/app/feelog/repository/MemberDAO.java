package com.app.feelog.repository;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

    public Optional<MemberDTO> getMemberByEmail(String memberEmail) {
        return memberMapper.getMemberByEmail(memberEmail);
    }

    public void kakaoJoin(MemberDTO memberDTO) {
        memberMapper.kakaoJoin(memberDTO);
    }
}
