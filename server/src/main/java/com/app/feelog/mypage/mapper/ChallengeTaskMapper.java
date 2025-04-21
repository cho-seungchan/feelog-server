package com.app.feelog.mypage.mapper;

import com.app.feelog.domain.vo.*;
import com.app.feelog.util.EightRowPagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ChallengeTaskMapper {

    // 2025.04.16 조승찬  :: 공통 과제 생성하기
    void insertCommonTask(CommonTaskVO common);

    // 2025.04.16 조승찬  :: 개인과제 풀의 최대 아이디 가져오기 (랜덤 값 산출용)
    Long getmaxIdOfTask();

    // 2025.04.16 조승찬  :: 랜덤으로 산출한 아이디에 해당한느 과제가 있는지 확인
    int checkIfExists(@Param("id") Long id);

    // 2025.04.16 조승찬  :: 랜덤으로 산출한 과제를 모든 멤버의 과제로 생성
    void insertMemberTask(@Param("id") Long id);

    // 2025.04.19 조승찬 :: 개인과제 가져오기
    Optional<MemberTaskPoolVO> getMemberTask(Long memberId);

    // 2025.04.20 조승찬 :: 개별 챌린지 가져오기
    Optional<MemberChallengeVO> getMemberChallenge(@Param("memberId") Long memberId, @Param("taskId") Long taskId);

    // 2025.04.19 조승찬 :: 공통 과제 가져오기
    List<CommonTaskVO> getCommonTasks();

    // 2025.04.20 조승찬 :: 공통 챌린지 가져오기
    Optional<CommonChallengeVO> getCommonChallenge(@Param("memberId") Long memberId, @Param("taskId") Long taskId);

    // 2025.04.19 조승찬 :: 챌린지 슈퍼키 생성
    void insertChallenge(ChallengeVO challenge);

    // 2025.04.19 조승찬 :: 개별 챌린지 생성
    void insertMemberChallege(@Param("id") Long id, @Param("memberId") Long memberId, @Param("taskId") Long taskId);

    // 2025.04.20 조승찬 :: 개별 챌린지 생성 (중단 후 재도전일 경우)
    void rePostMemberChallenge(Long id);

    // 2025.04.19 조승찬 :: 공통 챌린지 생성
    void insertCommonChallege(@Param("id") Long id, @Param("memberId") Long memberId, @Param("taskId") Long taskId);

    // 2025.04.20 조승찬 :: 공통 챌린지 생성 (중단 후 재도전일 경우)
    void rePostCommonChallenge(Long id);

    // 2025.04.20 조승찬 :: 개별 과제 중단
    void cancelChallenge(Long id);

    // 2025.04.20 조승찬 :: 페이지네이션용 전체건수 가져오기
    int getCountAll(Long memberId);

    // 2025.04.20 조승찬 :: 챌린지 내역 가져오기
    List<CommonChallengeVO> getChallengingList(@Param("memberId")Long memberId, @Param("pagination") EightRowPagination pagination);

    // 2025.04.20 조승찬 :: 챌린지 내역에 헤당하는 개별 과제 정보 가져오기
    Optional<MemberTaskPoolVO> getMemberTaskInfo(Long id);

    // 2025.04.20 조승찬 :: 챌린지 내역에 헤당하는 공통 과제 정보 가져오기
    Optional<CommonTaskVO> getCommonTaskInfo(Long id);

    // 2025.04.22 조승찬 :: 챌린지 완료 처리
    void completeChallenge(Long id);
}
