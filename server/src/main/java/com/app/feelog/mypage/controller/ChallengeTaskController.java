// 2025.04.17 조승찬  ::  챌린지

package com.app.feelog.mypage.controller;

import com.app.feelog.domain.dto.CommonTaskDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.MemberTaskPoolDTO;
import com.app.feelog.mypage.service.ChallengeTaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/challenge")
@Slf4j
public class ChallengeTaskController {
    private final HttpSession session;
    private final HttpServletRequest request;
    private final ChallengeTaskService challengeTaskService;

    // 2025.04.19 조승찬 ::  오늘의 챌린지 리스트
    @GetMapping("/task-list")
    public String getChallengeList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                   Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 개인별 과제 가져오기
        MemberTaskPoolDTO individual = challengeTaskService.getMemberTask(member.getId()).get();
        // 공통 과제 가져오기
        List<CommonTaskDTO> commons = challengeTaskService.getCommonTasks();

        model.addAttribute("individual", individual);
        model.addAttribute("commons", commons);

        return "challenge/task-list";
    }

    // 2025.04.19 조승찬 :: 개별과제 도전
    @ResponseBody
    @PostMapping("/member-challenge")
    public void postMemberChallenge(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    @RequestBody Map<String, String> requestBody) {

        String idString = requestBody.get("id");
        Long taskId = Long.parseLong(idString);
        challengeTaskService.postMemberChallenge(member.getId(),taskId);

    };


    // 2025.04.19 조승찬 :: 공통과제 도전
    @ResponseBody
    @PostMapping("/common-challenge")
    public void postCommonChallenge(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    @RequestBody Map<String, String> requestBody) {

        String idString = requestBody.get("id");
        Long taskId = Long.parseLong(idString);
        challengeTaskService.postCommonChallenge(member.getId(),taskId);

    };
}
