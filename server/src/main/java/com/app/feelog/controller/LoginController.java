// 2025.04.17 조승찬   ::  로그인

package com.app.feelog.controller;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
@Slf4j
public class LoginController {
    private final LoginService loginService;

//    @GetMapping("/login")
//    public String getLogin() {
//        return "login/login";
//    }
//
//    @GetMapping("/email-login")
//    public String getEmailLogin() {
//        return "login/email-login";
//    }
//
//    @ResponseBody
//    @PostMapping("/email-login")
//    public String postEmailLogin(@RequestBody MemberDTO memberDTO, RedirectAttributes redirectAttributes) {
//        // 이메일과 비밀번호가 같은 멤버 찾기
//        loginService.getMemberByEmailAndPassword(memberDTO);
//
//        return "login/email-login";
//    }
//
//    @GetMapping("/password-issue")
//    public String getPasswordIssue() {
//        return "login/password-issue";
//    }
}
