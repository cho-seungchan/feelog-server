package com.app.feelog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/official")
@Slf4j
public class OfficialController {

    @GetMapping("/official")
    public String getOfficial() {
        return "official/official";
    }

    @GetMapping("/official-notice")
    public String getOfficialNotice() {
        return "official/official-notice";
    }

    @GetMapping("/official-challenge")
    public String getOfficialChallenge() {
        return "official/official-challenge";
    }


}
