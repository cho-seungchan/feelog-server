// 2025.04.17 조승찬  ::  챌린지

package com.app.feelog.mypage.controller;

import com.app.feelog.domain.dto.*;
import com.app.feelog.mypage.dto.AllChallengeListDTO;
import com.app.feelog.mypage.dto.CommonTaskChallengeDTO;
import com.app.feelog.mypage.dto.MemberTaskPoolChallengeDTO;
import com.app.feelog.mypage.service.ChallengeTaskService;
import com.app.feelog.util.EightRowPagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        MemberTaskPoolChallengeDTO individual = challengeTaskService.getMemberTask(member.getId());
        // 공통 과제 가져오기
        List<CommonTaskChallengeDTO> commons = challengeTaskService.getCommonTasks(member.getId());

        model.addAttribute("individual", individual);
        model.addAttribute("commons", commons);

        return "challenge/task-list";
    }

    // 2025.04.19 조승찬 :: 개별과제 도전
    @ResponseBody
    @PostMapping("/member-challenge")
    public ResponseEntity<Map<String, Object>> postMemberChallenge(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                                                   @RequestBody MemberChallengeDTO memberChallengeDTO) {

        Long challengeId = null;
        if (memberChallengeDTO.getId() == 0 || memberChallengeDTO.getId() == null) {
            // 최초 도전일 경우 챌린지 데이타 생성
            challengeId = challengeTaskService.postMemberChallenge(member.getId(),memberChallengeDTO.getTaskId());

        }  else {
            // 중단 후 재 도전일 경우 챌린지 데이타 정보 수정
            challengeTaskService.rePostmemberChallenge(memberChallengeDTO.getId());
            challengeId = memberChallengeDTO.getId();
        }

        Map<String, Object> response = new HashMap<String, Object>();
        response.put("challengeId", challengeId);
        return ResponseEntity.ok(response);

    };

    // 2025.04.20 조승찬 :: 개별과제 중단
    @ResponseBody
    @PostMapping("/member-cancel")
    public void cancelMemberChallenge(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                      @RequestBody MemberChallengeDTO memberChallengeDTO) {

        challengeTaskService.cancelMemberChallenge(memberChallengeDTO.getId());

    };

    // 2025.04.19 조승찬 :: 공통과제 도전
    @ResponseBody
    @PostMapping("/common-challenge")
    public ResponseEntity<Map<String, Object>> postCommonChallenge(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                                                   @RequestBody CommonChallengeDTO commonChallengeDTO) {

        Long challengeId = null;
        if (commonChallengeDTO.getId() == 0 || commonChallengeDTO.getId() == null) {
            // 최초 도전일 경우 챌린지 데이타 생성
            challengeId = challengeTaskService.postCommonChallenge(member.getId(),commonChallengeDTO.getTaskId());
        }  else {
            // 중단 후 재 도전일 경우 챌린지 데이타 정보 수정
            challengeTaskService.rePostCommonChallenge(commonChallengeDTO.getId());
            challengeId = commonChallengeDTO.getId();
        }

        Map<String, Object> response = new HashMap<String, Object>();
        response.put("challengeId", challengeId);
        return ResponseEntity.ok(response);

    };


    // 2025.04.20 조승찬 :: 공통 과제 중단
    @ResponseBody
    @PostMapping("/common-cancel")
    public void cancelCommonChallenge(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                      @RequestBody CommonChallengeDTO commonChallengeDTO) {

        challengeTaskService.cancelCommonChallenge(commonChallengeDTO.getId());

    };

    // 2025.04.20 조승찬 :: 진행중인 챌린지 목록 화면
    @GetMapping("/challenging-list")
    public String getChallengingList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                     EightRowPagination pagination, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        List<AllChallengeListDTO> challenges = challengeTaskService.getChallengingList(member.getId(), pagination);

        model.addAttribute("challenges", challenges);
        model.addAttribute("pagination", pagination);

        return "challenge/challenging-list";
    }

    // 2025.04.21 조승찬 :: 개별 챌린지 완료 처리
    @ResponseBody
    @PostMapping("/member-complete")
    public void completeMemberChallenge(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                      @RequestBody MemberChallengeDTO memberChallengeDTO) {

        challengeTaskService.completeMemberChallenge(memberChallengeDTO.getId());

    };

    // 2025.04.21 조승찬 :: 개별 챌린지 완료 취소 처리
    @ResponseBody
    @PostMapping("/member-cancel-complete")
    public void cancelCompleteMemberChallenge(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                        @RequestBody MemberChallengeDTO memberChallengeDTO) {

        challengeTaskService.cancelCompleteMemberChallenge(memberChallengeDTO.getId());

    };

    // 2025.04.21 조승찬 :: 공통 챌린지 완료 처리
    @ResponseBody
    @PostMapping("/common-complete")
    public void completeCommonChallenge(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                        @RequestBody MemberChallengeDTO memberChallengeDTO) {

        challengeTaskService.completeCommonChallenge(memberChallengeDTO.getId());

    };

    // 2025.04.21 조승찬 :: 공통 챌린지 완료 취소 처리
    @ResponseBody
    @PostMapping("/common-cancel-complete")
    public void cancelCompleteCommonChallenge(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                              @RequestBody MemberChallengeDTO memberChallengeDTO) {

        challengeTaskService.cancelCompleteCommonChallenge(memberChallengeDTO.getId());

    };


    // 2025.04.21 조승찬 :: 완료된 챌린지 목록 화면
    @GetMapping("/completed-list")
    public String getCompletedList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                     EightRowPagination pagination, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        List<AllChallengeListDTO> challenges = challengeTaskService.getCompletedList(member.getId(), pagination);
        log.info("memberId :: "+member.getId());
        log.info("pagination :: "+pagination.toString());
        log.info("challenge-list :: "+challenges.toString());

        model.addAttribute("challenges", challenges);
        model.addAttribute("pagination", pagination);

        return "challenge/completed-list";
    }


}
