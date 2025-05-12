// 2025.04.17   조승찬 카카오 로그인으로 회원정보 가져오기

package com.app.feelog.mypage.controller;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.mypage.service.JoinService;
import com.app.feelog.mypage.service.KakaoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class KakaoController {

    private final HttpSession session;
    private final KakaoService kakaoService;
    private final JoinService joinService;

    // 2025.04.17 조승찬 :: 카카오 로그인
    @GetMapping("/kakao/login")
    public String login(String code) {
        // Authorization Code를 html a tag에서 요청해서 받아옴
        // Authorization Code로 Access Token 요청
        String token = kakaoService.getKakaoAccessToken(code);
        // Access Token으로 사용자 정보 요청
        Optional<MemberDTO> kakaoInfo = kakaoService.getKakaoInfo(token);

        log.info("kakaoInfo: {}", kakaoInfo);

        MemberDTO memberDTO = kakaoInfo.orElseThrow(() -> new RuntimeException("카카오 사용자 정보 조회 실패"));

        log.info("to getMemberByEmail");
        Optional<MemberDTO> foundMember = joinService.getMemberByEmail(memberDTO.getMemberEmail());
        log.info("from getMemberByEmail");

        if (!foundMember.isPresent()) {
            log.info("member not found");
            joinService.kakaoJoin(memberDTO);
            foundMember = joinService.getMemberByEmail(memberDTO.getMemberEmail());
            joinService.insertMemberTask(foundMember.get().getId());
        }

        session.setAttribute("memberStatus", "kakao");
        session.setAttribute("member", foundMember.get());

        log.info("memberDTO: {}", memberDTO);

        String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
        if (redirectUrl != null) {
            session.removeAttribute("redirectAfterLogin");
            return "redirect:" + redirectUrl;
        }

        return "redirect:/";
    }
}
