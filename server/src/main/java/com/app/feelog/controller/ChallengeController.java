// 2025.04.17 조승찬  ::  챌린지

package com.app.feelog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/challenge")
@Slf4j
public class ChallengeController {

    @GetMapping("/challenge-list")
    public String getChallengeList() {
        return "challenge/challenge-list";
    }

    @GetMapping("/challenge-detail")
    public String getChallengeDetail() {
        return "challenge/challenge-detail";
    }
}
