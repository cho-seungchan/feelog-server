// 2025.04.17 조승찬   ::  회원가입

package com.app.feelog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/join")
@Slf4j
public class JoinController {

    @GetMapping("/join")
    public String getJoin() {
        return "join/join";
    }

    @GetMapping("/email-join")
    public String getEmail() {
        return "join/email-join";
    }

    @GetMapping("/certifying")
    public String getCertifying() {
        return "join/certifying";
    }

    @GetMapping("/certified")
    public String getCertified() {
        return "join/certified";
    }
}
