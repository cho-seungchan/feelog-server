package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryScoreDTO;
import com.app.feelog.repository.DiaryScoreDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryScoreServiceImpl implements DiaryScoreService {

    private final DiaryScoreDAO diaryScoreDAO;

    @Override
    public Optional<DiaryScoreDTO> getScoreById(Long id) {
        return diaryScoreDAO.findById(id)
                .map(this::toDTO);
    }

    @Override
    public int getEmotionScore(String contents) {
        // 외부 감정 분석 API를 호출하기 위한 RestTemplate 객체 생성
        RestTemplate restTemplate = new RestTemplate();

        // 호출할 API의 URL 설정

        String url = "http://13.125.248.28/api/feeling-check";

        // 요청 바디에 보낼 데이터 구성 (JSON으로 직렬화될 Map 형태)
        Map<String, String> body = new HashMap<>();
        body.put("contents", contents); // key는 'contents', value는 제목+내용 합친 문자열

        // HTTP 요청 헤더 설정: Content-Type을 JSON으로 지정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HttpEntity로 요청 본문과 헤더를 함께 묶음
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        // POST 방식으로 외부 감정 분석 API 호출, 응답 타입은 Map으로 받음
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        // 응답 받은 Map에서 "score" 값을 꺼내서 정수로 반환
        return (int) response.getBody().get("score");
    }

}
