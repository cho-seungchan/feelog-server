// 2025.04.16 조승찬
package com.app.feelog.service;

import com.app.feelog.domain.dto.CommonTaskDTO;
import com.app.feelog.repository.ChallengeDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeDAO challengeDAO;

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

        challengeDAO.insertCommonTask(common.toVO());
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

        challengeDAO.insertCommonTask(common.toVO());
    }
}
