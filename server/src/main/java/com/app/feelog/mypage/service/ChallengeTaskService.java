// 2025.04.16 조승찬
package com.app.feelog.mypage.service;

import com.app.feelog.domain.dto.MemberTaskPoolDTO;
import com.app.feelog.domain.vo.*;
import com.app.feelog.mypage.dto.AllChallengeListDTO;
import com.app.feelog.mypage.dto.CommonTaskChallengeDTO;
import com.app.feelog.mypage.dto.MemberTaskPoolChallengeDTO;
import com.app.feelog.mypage.repository.ChallengeTaskDAO;
import com.app.feelog.domain.dto.CommonTaskDTO;
import com.app.feelog.util.EightRowPagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChallengeTaskService implements ToDTO{
    private final ChallengeTaskDAO challengeTaskDAO;
    private final AllChallengeListDTO allChallengeListDTO;

    // 2025.04.16 조승찬 :: 서울시 공원 데이타를 공통 과제에 넣기
    public void challengePark(int index, JSONObject row) {

        CommonTaskDTO common = new CommonTaskDTO();
        common.setCommonTaskName(row.getString("P_PARK"));              // 이름
        common.setCommonTaskImg(row.getString("P_IMG"));                // 이미지
        if (common.getCommonTaskImg() == null || common.getCommonTaskImg().equals("")) {
            common.setCommonTaskImg(row.getString("GUIDANCE"));          // 안내도
        }
        common.setCommonTaskTell(row.getString("P_ADMINTEL"));          // 전화번호
        common.setCommonTaskUrl(row.getString("TEMPLATE_URL"));         // 바로가기 URL
        common.setCommonTaskAddr(row.getString("P_ADDR"));              // 주소
        common.setCommonTaskLot(row.optDouble("LONGITUDE", 0.0));    // X좌표(WGS84)
        common.setCommonTaskLat(row.optDouble("LATITUDE", 0.0));      // Y좌표(WGS84)

        common.setCommonTaskServiceName("SearchParkInfoService");
        common.setCommonTaskReqPage(String.valueOf(index));

        challengeTaskDAO.insertCommonTask(common.toVO());
    }

    // 2025.04.16 조승찬 :: 서울시 문화 공간 데이타를 공통 과제 생성
    public void challengeIndoor(int index, JSONObject row) {

        CommonTaskDTO common = new CommonTaskDTO();
        common.setCommonTaskName(row.getString("FAC_NAME"));              // 이름
        common.setCommonTaskImg(row.getString("MAIN_IMG"));               // 이미지
        common.setCommonTaskTell(row.getString("PHNE"));                 // 전화번호
        common.setCommonTaskUrl(row.getString("HOMEPAGE"));             // 바로가기 URL
        common.setCommonTaskAddr(row.getString("ADDR"));                // 주소
        common.setCommonTaskLot(row.optDouble("X_COORD", 0.0));    // X좌표(WGS84)
        common.setCommonTaskLat(row.optDouble("Y_COORD", 0.0));      // Y좌표(WGS84)

        common.setCommonTaskServiceName("culturalSpaceInfo");
        common.setCommonTaskReqPage(String.valueOf(index));

        challengeTaskDAO.insertCommonTask(common.toVO());
    }

    // 2025.04.16 조승찬 :: 개인과제 생성하기
    public void challengePrivate() {

        // 개인 task pool에서 최대 id 가져오기
        Long maxIdOfTask = challengeTaskDAO.getmaxIdOfTask();

        boolean isTask = false;
        Long id = 0l;
        while(!isTask) {

            // 랜덤 인덱스 추출
            Random random = new Random();
            id = random.nextLong(maxIdOfTask) + 1;

            // 해당 id에 task가 존재하는지 확인
            if (challengeTaskDAO.checkIfExists(id) == 1){
                isTask = true;
            }
        }

        // 멤버 task 생성
        challengeTaskDAO.insertMemberTask(id);

    }

    // 2025.04.19 조승찬 :: 개인과제 가져오기
    public MemberTaskPoolChallengeDTO getMemberTask(Long memberId) {

        // 멤버 타스크 가져오기
        MemberTaskPoolVO memberTaskPoolVO = challengeTaskDAO.getMemberTask(memberId)
                .orElse(null);
        // 멤버 챌린지 가져오기 :: 오늘 날짜 데이타가 있으면 가져오면 됨
        MemberChallengeVO memberChallengeVO = challengeTaskDAO.getMemberChallenge(memberId, memberTaskPoolVO.getId())
                .orElse(null);

        // 화면으로 보낼 DTO 만들기
        return toMemberTaskPoolChallengeDTO(memberChallengeVO, memberTaskPoolVO);
    }

    // 2025.04.19 조승찬 :: 공통 과제 가져오기
    public List<CommonTaskChallengeDTO> getCommonTasks(Long memberId) {

        // 공통 타스크 가져오기
        List<CommonTaskVO> commonTasks = challengeTaskDAO.getCommonTasks();
        // 공통 챌린지 가져오서 화면으로 보낼 DTO 만들기
        List<CommonTaskChallengeDTO> commonTaskChallenges = new ArrayList<>();
        commonTasks.forEach( task -> {
            CommonChallengeVO commonChallengeVO = challengeTaskDAO.getCommonChallenge(memberId, task.getId())
                    .orElse(null);
            commonTaskChallenges.add(toCommonTaskChallengeDTO(commonChallengeVO, task));
        });

        return commonTaskChallenges;
    }

    // 2025.04.19 조승찬 :: 개별 챌린지 생성 (최초 도전일 경우)
    public Long postMemberChallenge(Long memberId, Long taskId) {
        return challengeTaskDAO.postMemberChallenge(memberId, taskId);
    }

    // 2025.04.20 조승찬 :: 개인 챌린지 생성 (중단 후 재도전일 경우)
    public void rePostmemberChallenge(Long id) {
        challengeTaskDAO.rePostMemberChallenge(id);
    }

    // 2025.04.19 조승찬 :: 공통 챌린지 생성 (최초 도전일 경우)
    public Long postCommonChallenge(Long memberId, Long taskId) {
        return challengeTaskDAO.postCommonChallenge(memberId, taskId);
    }

    // 2025.04.20 조승찬 :: 공통 챌린지 생성 (중단 후 재도전일 경우)
    public void rePostCommonChallenge(Long id) {
        challengeTaskDAO.rePostCommonChallenge(id);
    }

    // 2025.04.20 조승찬 :: 개별 과제 중단
    public void cancelMemberChallenge(Long id) {
        challengeTaskDAO.cancelMemberChallenge(id);
    }

    // 2025.04.20 조승찬 :: 공통 과제 중단
    public void cancelCommonChallenge(Long id) {
        challengeTaskDAO.cancelCommonChallenge(id);
    }

    // 2025.04.20 조승찬 :: 진행중 챌린지 리스트 가져오기
    public List<AllChallengeListDTO> getChallengingList(Long memberId, EightRowPagination pagination) {

        log.info("total count :: "+challengeTaskDAO.getCountAll(memberId));
        pagination.create(challengeTaskDAO.getCountAll(memberId));
        List<CommonChallengeVO> challengings = challengeTaskDAO.getChallengingList(memberId, pagination);

        List<AllChallengeListDTO> allChallengeList = new ArrayList<>();
        challengings.forEach(challenging -> {
            MemberTaskPoolVO memberTask = challengeTaskDAO.getMemberTaskInfo(challenging.getId())
                    .orElse(null);
            CommonTaskVO     commonTask = challengeTaskDAO.getCommonTaskInfo(challenging.getId())
                    .orElse(null);
            allChallengeList.add(toAllChallengeListDTO(challenging,memberTask, commonTask));
        });

        return allChallengeList;
    }
}
