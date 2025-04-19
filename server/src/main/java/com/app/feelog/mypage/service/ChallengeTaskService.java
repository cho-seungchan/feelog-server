// 2025.04.16 조승찬
package com.app.feelog.mypage.service;

import com.app.feelog.domain.dto.MemberTaskPoolDTO;
import com.app.feelog.mypage.repository.ChallengeTaskDAO;
import com.app.feelog.domain.dto.CommonTaskDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChallengeTaskService implements ToDTO{
    private final ChallengeTaskDAO challengeTaskDAO;

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
    public Optional<MemberTaskPoolDTO> getMemberTask(Long memberId) {

        return Optional.ofNullable(
                toMemberTaskPoolDTO(challengeTaskDAO.getMemberTask(memberId)
                        .orElse(null)));
    }

    // 2025.04.19 조승찬 :: 공통 과제 가져오기
    public List<CommonTaskDTO> getCommonTasks() {

        return challengeTaskDAO.getCommonTasks()
            .stream()
            .map(this::toCommonTaskDTO)
            .collect(Collectors.toList());
    }

    public void postMemberChallenge(Long memberId, Long taskId) {
        challengeTaskDAO.postMemberChallenge(memberId, taskId);
    }

    public void postCommonChallenge(Long memberId, Long taskId) {
        challengeTaskDAO.postCommonChallenge(memberId, taskId);

    }
}
