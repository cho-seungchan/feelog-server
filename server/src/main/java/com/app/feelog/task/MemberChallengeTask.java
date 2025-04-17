// 2025. 4. 17 조승찬 개인과제 풀에서 과제를 랜덤으로 선택해서 전체 회원 과제로 제공

package com.app.feelog.task;

import com.app.feelog.service.ChallengeService;
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
public class MemberChallengeTask {

    private final ChallengeService challengeService;

    @SneakyThrows
    @Scheduled(cron = "0 35 * * * *")
    public void park() throws UnsupportedEncodingException {

        System.out.println("======================================");
        System.out.println(" 개인 챌린지 선정 작업 시작................");
        System.out.println(" 개인 선정 작업 시작................");
        System.out.println("======================================");

        challengeService.challengePrivate();

        System.out.println("======================================");
        System.out.println(" 개인 챌린지 선정 작업 종료................");
        System.out.println(" 개인 챌린지 선정 작업 종료................");
        System.out.println("======================================");


    }

}
