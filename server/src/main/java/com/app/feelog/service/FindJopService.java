package com.app.feelog.service;

import com.app.feelog.domain.dto.FindJopDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindJopService {
    @SneakyThrows
    public List<FindJopDTO> getFindJopData() throws IOException {
        List<FindJopDTO> jobs = new ArrayList<>();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B490007/worldjob30/openApi30"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=4CsqSsgQeBJ4zjD1AxNrCuLLbpigjJgZdCynQN24uJ9qNBw4KUE8dNByPu3kDEdiu652QhyKw5VVAGR1V%2FW7Dg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/

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

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        String jsonData = sb.toString();
        JSONObject jsonObject = XML.toJSONObject(jsonData); // XML을 JSON으로 변환
        log.info("Converted JSON: {}", jsonObject);

        // JSON 경로 탐색
        JSONObject body = jsonObject.getJSONObject("WORLDJOB").getJSONObject("BODY");
        JSONArray items = body.getJSONArray("ITEM");

        // 각 항목을 DTO 객체로 변환
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            FindJopDTO job = new FindJopDTO(
                    item.getString("rctntcOriginNm"),  // 모집 기관
                    item.getString("rctntcVisaNm"),    // 비자 유형
                    item.getString("rctntcLang"),      // 요구 언어 수준
                    item.getString("careerStleNm"),    // 경력 여부
                    item.getString("rctntcSj"),        // 채용 제목
                    item.getString("rctntcEndDe"),     // 모집 종료일
                    item.getString("rctntcBgnDe"),     // 모집 시작일
                    item.getString("rctntcNationNm"),  // 모집 국가
                    item.getString("entNm"),           // 기업명
                    item.getString("lplcKscoNm"),      // 업종
                    item.getInt("rctntcNmprCo")        // 모집 인원
            );
            jobs.add(job);
        }
        return jobs;
    }
}
