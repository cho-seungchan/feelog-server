// 2025. 4. 17 조승찬 개인과제 풀에서 과제를 랜덤으로 선택해서 전체 회원 과제로 제공

package com.app.feelog.task;

import com.app.feelog.mypage.service.ChallengeTaskService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberChallengeTask {

    private final ChallengeTaskService challengeService;

    @SneakyThrows
    @Scheduled(cron = "0 1 * * * *")
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
