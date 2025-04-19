package com.app.feelog.mypage.mapper;

import com.app.feelog.domain.vo.ChallengeVO;
import com.app.feelog.domain.vo.CommonTaskVO;
import com.app.feelog.domain.vo.MemberTaskPoolVO;
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

    // 2025.04.19 조승찬 :: 공통 과제 가져오기
    List<CommonTaskVO> getCommonTasks();

    // 2025.04.19 조승찬 :: 챌린지 슈퍼키 생성
    void insertChallenge(ChallengeVO challenge);

    // 2025.04.19 조승찬 :: 챌린지 중단
    void cancelChallenge(Long id);

    // 2025.04.19 조승찬 :: 챌린지 완료
    void completeChallenge(Long id);

    // 2025.04.19 조승찬 :: 개별 챌린지 생성
    void insertMemberChallege(@Param("id") Long id, @Param("memberId") Long memberId, @Param("taskId") Long taskId);

    // 2025.04.19 조승찬 :: 공통 챌린지 생성
    void insertCommonChallege(@Param("id") Long id, @Param("memberId") Long memberId, @Param("taskId") Long taskId);

}
