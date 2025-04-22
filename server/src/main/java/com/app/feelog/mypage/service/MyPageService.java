// 2021.04.21 조승찬
package com.app.feelog.mypage.service;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.mypage.repository.MyPageDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyPageService implements ToDTO {

    private final MyPageDAO myPageDAO;

    // 2025.04.21  조승찬 :: 프로필 수정
    public void postSettingProfile(MemberDTO memberDTO) {
        myPageDAO.postSettingProfile(memberDTO.toVO());
    }

    // 2025.04.21 조승찬 :: 아이디로 멤버 정보 가져오기
    public MemberDTO getMemberById(Long id) {

        return toMemberDTO(myPageDAO.getMemberById(id)
                        .orElse(null));
    }

    // 2025.04.22  조승찬 :: 알림정보 수정
    public void postSettingNotify(MemberDTO memberDTO) {
        myPageDAO.postSettingNotify(memberDTO.toVO());
    }
}
