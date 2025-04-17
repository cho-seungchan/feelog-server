// 2025.04.17 조승찬   ::  로그인

package com.app.feelog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String getLogin() {
        return "login/login";
    }

    @GetMapping("/email-login")
    public String getEmail() {
        return "login/email-login";
    }

    @GetMapping("/password-issue")
    public String getPasswordIssue() {
        return "login/password-issue";
    }
}
