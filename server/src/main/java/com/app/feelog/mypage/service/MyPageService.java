// 2021.04.21 조승찬
package com.app.feelog.mypage.service;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.mypage.repository.MyPageDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyPageService {

    private final MyPageDAO myPageDAO;

    // 2025.04.21  조승찬 :: 프로필 수정
    public void postSettingProfile(MemberDTO memberDTO) {
        myPageDAO.postSettingProfile(memberDTO.toVO());
    }
}
