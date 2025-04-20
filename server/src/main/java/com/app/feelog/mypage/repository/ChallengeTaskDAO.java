// 2025.04.16  조승찬
package com.app.feelog.mypage.repository;

import com.app.feelog.domain.vo.*;
import com.app.feelog.mypage.mapper.ChallengeTaskMapper;
import com.app.feelog.util.EightRowPagination;
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

    // 2025.04.20 조승찬 :: 개별 챌린지 가져오기
    public Optional<MemberChallengeVO> getMemberChallenge(Long memberId, long taskId) {
        return Optional.ofNullable(
                challengeTaskMapper.getMemberChallenge(memberId, taskId)
                        .orElse(null));
    }

    // 2025.04.19 조승찬 :: 공통 과제 가져오기
    public List<CommonTaskVO> getCommonTasks() {
        return challengeTaskMapper.getCommonTasks();
    }

    // 2025.04.20 조승찬 :: 공통 챌린지 가져오기
    public Optional<CommonChallengeVO> getCommonChallenge(Long memberId, Long taskId) {
        return Optional.ofNullable(
                challengeTaskMapper.getCommonChallenge(memberId, taskId)
                        .orElse(null));
    }

    // 2025.04.19 조승찬 :: 개별 챌린지 생성 (최초 도전)
    public Long postMemberChallenge(Long memberId, Long taskId) {

        ChallengeVO challenge = new ChallengeVO();
        // 챌린지 슈퍼키 생성
        challengeTaskMapper.insertChallenge(challenge);
        // 개별 챌린지 생성
        challengeTaskMapper.insertMemberChallege(challenge.getId(),memberId,taskId);
        // 챌린지 중단을 위해 사용할 id 리턴
        return challenge.getId();
    }

    // 2025.04.20 조승찬 :: 개별 챌린지 생성 (중단 후 재도전일 경우)
    public void rePostMemberChallenge(Long id) {
        challengeTaskMapper.rePostMemberChallenge(id);
    }

    // 2025.04.19 조승찬 :: 공통 챌린지 생성 (최초 도전)
    public Long postCommonChallenge(Long memberId, Long taskId) {

        ChallengeVO challenge = new ChallengeVO();
        // 챌린지 슈퍼키 생성
        challengeTaskMapper.insertChallenge(challenge);
        // 개별 챌린지 생성
        challengeTaskMapper.insertCommonChallege(challenge.getId(),memberId,taskId);
        // 챌린지 중단을 위해 사용할 id 리턴
        return challenge.getId();
    }

    // 2025.04.20 조승찬 :: 공통 챌린지 생성 (중단 후 재도전일 경우)
    public void rePostCommonChallenge(Long id) {
        challengeTaskMapper.rePostCommonChallenge(id);
    }

    // 2025.04.20 조승찬 :: 개별 과제 중단
    public void cancelMemberChallenge(Long id) {
        challengeTaskMapper.cancelChallenge(id);
    }

    // 2025.04.20 조승찬 :: 공통 과제 중단
    public void cancelCommonChallenge(Long id) {
        challengeTaskMapper.cancelChallenge(id);
    }

    // 2025.04.20 조승찬 :: 페이지네이션을 위한 전체 건수 가져오기
    public int getCountAll(Long memberId) {
        return challengeTaskMapper.getCountAll(memberId);
    }

    // 2025.04.20 조승찬 :: 현재 진행중인 챌린지 리스트 가져오기
    public List<CommonChallengeVO> getChallengingList(Long memberId, EightRowPagination pagination) {
        return challengeTaskMapper.getChallengingList(memberId, pagination);
    }

    public Optional<MemberTaskPoolVO> getMemberTaskInfo(Long id) {
        return Optional.ofNullable(
                challengeTaskMapper.getMemberTaskInfo(id)
                        .orElse(null));
    }

    public Optional<CommonTaskVO> getCommonTaskInfo(Long id) {
        return Optional.ofNullable(
                challengeTaskMapper.getCommonTaskInfo(id)
                        .orElse(null));
    }
}
