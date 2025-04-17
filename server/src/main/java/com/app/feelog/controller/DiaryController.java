package com.app.feelog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diary")
@RequiredArgsConstructor
@Slf4j
public class DiaryController {

    @GetMapping("/all-diary")
    public void goToAllDiary(){};

    @GetMapping("/diary-read")
    public void goToDiaryRead(){};

    @GetMapping("/my-diary")
    public void goToMyDiary(){};
}
