// 2025.04.16  조승찬
package com.app.feelog.mypage.repository;

import com.app.feelog.domain.vo.ChallengeVO;
import com.app.feelog.domain.vo.MemberTaskPoolVO;
import com.app.feelog.mypage.mapper.ChallengeTaskMapper;
import com.app.feelog.domain.vo.CommonTaskVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ChallengeTaskDAO {
    private final ChallengeTaskMapper challengeTaskMapper;

    // 2025.04.16 조승찬  :: 공통 과제 생성하기
    public void insertCommonTask(CommonTaskVO common) {
        challengeTaskMapper.insertCommonTask(common);
    }

    // 2025.04.16 조승찬  :: 개인과제 풀의 최대 아이디 가져오기 (랜덤 값 산출용)
    public Long getmaxIdOfTask() {
        return challengeTaskMapper.getmaxIdOfTask();
    };

    // 2025.04.16 조승찬  :: 랜덤으로 산출한 아이디에 해당한느 과제가 있는지 확인
    public int checkIfExists(Long id) {
        return challengeTaskMapper.checkIfExists(id);
    }

    // 2025.04.16 조승찬  :: 랜덤으로 산출한 과제를 모든 멤버의 과제로 생성
    public void insertMemberTask(Long id) {
        challengeTaskMapper.insertMemberTask(id);
    }

    // 2025.04.19 조승찬 :: 개인과제 가져오기
    public Optional<MemberTaskPoolVO> getMemberTask(Long memberId) {
        return Optional.ofNullable(
                challengeTaskMapper.getMemberTask(memberId)
                        .orElse(null));
    }

    // 2025.04.19 조승찬 :: 공통 과제 가져오기
    public List<CommonTaskVO> getCommonTasks() {
        return challengeTaskMapper.getCommonTasks();
    }

    public void postMemberChallenge(Long memberId, Long taskId) {

        ChallengeVO challenge = new ChallengeVO();
        // 챌린지 슈퍼키 생성
        challengeTaskMapper.insertChallenge(challenge);
        // 개별 챌린지 생성
        challengeTaskMapper.insertMemberChallege(challenge.getId(),memberId,taskId);
    }

    public void postCommonChallenge(Long memberId, Long taskId) {

        ChallengeVO challenge = new ChallengeVO();
        // 챌린지 슈퍼키 생성
        challengeTaskMapper.insertChallenge(challenge);
        // 개별 챌린지 생성
        challengeTaskMapper.insertCommonChallege(challenge.getId(),memberId,taskId);
    }
}
