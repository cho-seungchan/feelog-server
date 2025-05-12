// 2025.04.17 조승찬   ::  회원가입

package com.app.feelog.mypage.controller;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.mypage.service.EmailService;
import com.app.feelog.mypage.service.JoinService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/join")
@Slf4j
public class joinController {
    private final JoinService joinService;
    private final EmailService emailService;

    // 2025.04.17 조승찬 :: 회원가입 화면
    @GetMapping("/join")
    public String getJoin() {
        return "join/join";
    }

    // 2025.04.18 조승찬 :: 이메일 회원 가입 화면
    @GetMapping("/email-join")
    public String getEmailJoin(Model model) {
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO", memberDTO);
        return "join/email-join";
    }

    // 2025.04.18 조승찬 :: 이메일 회원 가입 화면
    @PostMapping("/email-join")
    public String postEmailJoin(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {

        // 이메일 중복 체크
        if (joinService.getMemberByEmail(memberDTO.getMemberEmail()).isPresent()) {
            model.addAttribute("emailErrorMessage", "중복된 이메일입니다.");
            return "join/email-join";
        }

        // 닉네임 중복 체크
        if (joinService.getMemberByNickname(memberDTO.getMemberNickname()).isPresent()) {
            model.addAttribute("nicknameErrorMessage", "중복된 닉네임입니다.");
            return "join/email-join";
        }

        // 입력된 값 유지
        session.setAttribute("signupEmail", memberDTO.getMemberEmail());
        session.setAttribute("signupNickname", memberDTO.getMemberNickname());
        session.setAttribute("signupPassword", memberDTO.getMemberPassword());

        return "redirect:/join/certifying";
    }

    // 2025.04.18 조승찬 :: 이메일 확인 전송 화면
    @GetMapping("/certifying")
    public String getCertifying(HttpSession session, Model model) {

        // 입력된 값 받아오기
        String email = (String) session.getAttribute("signupEmail");
        String nickname = (String) session.getAttribute("signupNickname");
        String password = (String) session.getAttribute("signupPassword");

        if (email == null) {
            return "redirect:/join/email-join?error=session_expired";
        }

        model.addAttribute("email", email);

        // 입력된 값 유지
        session.setAttribute("signupEmail", email);
        session.setAttribute("signupNickname", nickname);
        session.setAttribute("signupPassword", password);

        return "join/certifying";
    }

    // 2025.04.18 조승찬 :: 확인 이메일 전송
    @ResponseBody
    @PostMapping("/certifying")
    public String postCertifying(HttpSession session, Model model, HttpServletResponse response) throws MessagingException {

        // 입력된 값 받아오기
        String email = (String) session.getAttribute("signupEmail");
        String nickname = (String) session.getAttribute("signupNickname");
        String password = (String) session.getAttribute("signupPassword");

        // 확인 메일 보내기
        emailService.sendCertifyingEmail(email, response);

        model.addAttribute("email", email);
        // 입력된 값 유지
        session.setAttribute("signupEmail", email);
        session.setAttribute("signupNickname", nickname);
        session.setAttribute("signupPassword", password);

        return "join/certifying";
    }

    // 2025.04.18 조승찬 :: 이메일에서 보내온 토큰 일치 확인
    @GetMapping("/certified")
    public String getCertified(@CookieValue(name = "token", required = false) String token,
                               @RequestParam String code,
                               HttpServletResponse response, HttpSession session, Model model) {

        // 토큰이 만료되었을 때(유효기간이 지났을 때)
        if (token == null || token.isEmpty()) {
            model.addAttribute("errorMessage", "10분이 지났습니다.");
            return "join/certifying";
        }

        // 발급받은 토큰이 동일하다면 쿠키 초기화
        if(token.equals(code)) {
            Cookie cookie = new Cookie("token", "");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);

            // 입력된 값 받아와서 멤버 테이블에 저장
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setMemberEmail((String) session.getAttribute("signupEmail"));
            memberDTO.setMemberNickname((String) session.getAttribute("signupNickname"));
            memberDTO.setMemberPassword((String) session.getAttribute("signupPassword"));
            joinService.emailJoin(memberDTO);

            // 로그인 상태 유지
            MemberDTO member = joinService.getMemberByEmail(memberDTO.getMemberEmail()).get();
            joinService.insertMemberTask(memberDTO.getId());
            session.setAttribute("memberStatus", "email");
            session.setAttribute("member", member);

        } else {
            model.addAttribute("errorMessage", "정보가 불일치 합니다.");
            return "join/certifying";
        }

        // 세션 클리어
        session.removeAttribute("signupEmail");
        session.removeAttribute("signupNickname");
        session.removeAttribute("signupPassword");

        return "join/certified";
    }

}
