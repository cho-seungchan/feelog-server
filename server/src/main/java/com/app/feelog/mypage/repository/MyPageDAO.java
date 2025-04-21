// 2025.04.21 조승찬
package com.app.feelog.mypage.repository;

import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.mypage.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MyPageDAO {
    private final MyPageMapper myPageMapper;

    public void postSettingProfile(MemberVO memberVOvo) {
        myPageMapper.postSettingProfile(memberVOvo);
    }
}

