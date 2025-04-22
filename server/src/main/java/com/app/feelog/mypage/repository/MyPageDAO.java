// 2025.04.21 조승찬
package com.app.feelog.mypage.repository;

import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.mypage.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MyPageDAO {
    private final MyPageMapper myPageMapper;

    // 2025.04.21  조승찬 :: 프로필 수정
    public void postSettingProfile(MemberVO memberVOvo) {
        myPageMapper.postSettingProfile(memberVOvo);
    }

    // 2025.04.21 조승찬 :: 아이디로 멤버 정보 가져오기
    public Optional<MemberVO> getMemberById(Long id) {
        return Optional.ofNullable(
                myPageMapper.getMemberById(id)
                        .orElse(null));
    }

    // 2025.04.22  조승찬 :: 알림정보 수정
    public void postSettingNotify(MemberVO memberVO) {
        myPageMapper.postSettingNotify(memberVO);
    }
}

