// 2025. 4. 16 조승찬 서울시 공공데이타 가져와서 공통 과제 생성

package com.app.feelog.task;

import com.app.feelog.mypage.service.ChallengeTaskService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommonChallengeTask {

    private final ChallengeTaskService challengeService;

    @SneakyThrows
    @Scheduled(cron = "0 0 6 * * *")
    public void park() throws UnsupportedEncodingException {

        System.out.println("======================================");
        System.out.println(" 공통 챌린지 선정 작업 시작................");
        System.out.println(" 공통 챌린지 선정 작업 시작................");
        System.out.println("======================================");

        // 공원 선정하기
        selectPark();

        // 문화공간 선정하기
        selectIndoor();

        System.out.println("======================================");
        System.out.println(" 공통 챌린지 선정 작업 종료................");
        System.out.println(" 공통 챌린지 선정 작업 종료................");
        System.out.println("======================================");


    }

    private void selectPark() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" + URLEncoder.encode("70424f77426a666b3130336468497559", "UTF-8")); // 인증키
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); // 요청파일타입
        urlBuilder.append("/" + URLEncoder.encode("SearchParkInfoService", "UTF-8")); // 서비스명

        // 총 갯수를 알기 위해서 1페이지 가져오기
        String jsonData = fetchData(urlBuilder, 1);

        // JSON 데이터 처리
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject SearchParkInfoService = jsonObject.getJSONObject("SearchParkInfoService");
        String resultCode = SearchParkInfoService.getJSONObject("RESULT").getString("CODE");

        // "CODE" 값이 "INFO-000"이 아니면 중단
        if (!"INFO-000".equals(resultCode)) {
            System.out.println(resultCode+" 코드가 감지되어 작업을 중단합니다. 11111");
            return ;
        }
        int totalCount = SearchParkInfoService.getInt("list_total_count");

        // 랜덤 인덱스 추출
        Random random = new Random();
        int index = random.nextInt(totalCount) + 1;
        // 해당 인덱스 데이터 확인하기
        jsonData = fetchData(urlBuilder, index);

        // JSON 데이터 처리
        jsonObject = new JSONObject(jsonData);
        SearchParkInfoService = jsonObject.getJSONObject("SearchParkInfoService");
        resultCode = SearchParkInfoService.getJSONObject("RESULT").getString("CODE");

        // "CODE" 값이 "INFO-000"이 아니면 중단
        if (!"INFO-000".equals(resultCode)) {
            System.out.println(resultCode+" 코드가 감지되어 작업을 중단합니다. 22222");
            return ;
        }

        // DB 입력
        JSONArray rows = SearchParkInfoService.getJSONArray("row");
        challengeService.challengePark(index, rows.getJSONObject(0));
    }

    private void selectIndoor() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" + URLEncoder.encode("70424f77426a666b3130336468497559", "UTF-8")); // 인증키
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); // 요청파일타입
        urlBuilder.append("/" + URLEncoder.encode("culturalSpaceInfo", "UTF-8")); // 서비스명

        // 총 갯수를 알기 위해서 1페이지 가져오기
        String jsonData = fetchData(urlBuilder, 1);

        // JSON 데이터 처리
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject SearchParkInfoService = jsonObject.getJSONObject("culturalSpaceInfo");
        String resultCode = SearchParkInfoService.getJSONObject("RESULT").getString("CODE");

        // "CODE" 값이 "INFO-000"이 아니면 중단
        if (!"INFO-000".equals(resultCode)) {
            System.out.println(resultCode+" 코드가 감지되어 작업을 중단합니다.");
            return ;
        }
        int totalCount = SearchParkInfoService.getInt("list_total_count");

        // 첫번째 랜덤 인덱스 추출
        Random random = new Random();
        int index1 = random.nextInt(totalCount) + 1;
        // 해당 인덱스 데이터 가져오기
        jsonData = fetchData(urlBuilder, index1);

        // JSON 데이터 처리
        jsonObject = new JSONObject(jsonData);
        SearchParkInfoService = jsonObject.getJSONObject("culturalSpaceInfo");
        resultCode = SearchParkInfoService.getJSONObject("RESULT").getString("CODE");

        // "CODE" 값이 "INFO-000"이 아니면 중단
        if (!"INFO-000".equals(resultCode)) {
            System.out.println(resultCode+" 코드가 감지되어 작업을 중단합니다.");
            return ;
        }

        // DB 입력
        JSONArray rows = SearchParkInfoService.getJSONArray("row");
        challengeService.challengeIndoor(index1, rows.getJSONObject(0));

        // 두번째 랜덤 인덱스 추출
        int index2;
        do {
            index2 = random.nextInt(totalCount) + 1;
        } while (index2 == index1);
        // 해당 인덱스 데이터 가져오기
        jsonData = fetchData(urlBuilder, index2);

        // JSON 데이터 처리
        jsonObject = new JSONObject(jsonData);
        SearchParkInfoService = jsonObject.getJSONObject("culturalSpaceInfo");
        resultCode = SearchParkInfoService.getJSONObject("RESULT").getString("CODE");

        // "CODE" 값이 "INFO-000"이 아니면 중단
        if (!"INFO-000".equals(resultCode)) {
            System.out.println(resultCode+" 코드가 감지되어 작업을 중단합니다.");
            return ;
        }

        // DB 입력
        rows = SearchParkInfoService.getJSONArray("row");
        challengeService.challengeIndoor(index2, rows.getJSONObject(0));
    }

    private String fetchData(StringBuilder urlBuilder, int index) throws IOException {

        // 해당 인덱스 데이타 가져오기
        if (index != 1){
            // 마지막 "/"의 위치 찾기
            int lastSlashIndex = urlBuilder.lastIndexOf("/");
            // 마지막 "/" 바로 이전 "/"의 위치 찾기
            int secondLastSlashIndex = urlBuilder.lastIndexOf("/", lastSlashIndex - 1);
            // 두 개의 값을 삭제
            urlBuilder.delete(secondLastSlashIndex, urlBuilder.length());
        }
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(index), "UTF-8")); // 요청 시작위치
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(index), "UTF-8")); // 요청 종료위치

        // HTTP 요청
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        String sb = rd.readLine();
        rd.close();
        conn.disconnect();

        return sb;
    }

}
