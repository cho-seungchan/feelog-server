// 2025.04.16  조승찬
package com.app.feelog.repository;

import com.app.feelog.domain.vo.CommonTaskVO;
import com.app.feelog.mapper.ChallengeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ChallengeDAO {
    private final ChallengeMapper challengeMapper;

    // 2025.04.16 조승찬  :: 공통 과제 생성하기
    public void insertCommonTask(CommonTaskVO common) {
        challengeMapper.insertCommonTask(common);
    }
}
