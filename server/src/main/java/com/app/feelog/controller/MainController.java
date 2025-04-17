package com.app.feelog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
@Slf4j
public class MainController {

    @GetMapping("/cs")
    public String getMainCs() {
        return "main/cs";
    }

    @GetMapping("/faq")
    public String getMainFaq() {
        return "main/faq";
    }

    @GetMapping("/intro")
    public String getMainIntro() {
        return "main/intro";
    }

    @GetMapping("/mind-log")
    public String getMainMindLog() {
        return "main/mind-log";
    }

    @GetMapping("/mind-log-edit")
    public String getMainMindLogEdit() {
        return "main/mind-log-edit";
    }

    @GetMapping("/new-blow")
    public String getNewBlog() {
        return "main/new-blog";
    }

    @GetMapping("/post")
    public String getMainPost() {
        return "main/post";
    }

    @GetMapping("/post-edit")
    public String getMainPostEdit() {
        return "main/post-edit";
    }

    @GetMapping("/ticket")
    public String getMainTicket() {
        return "main/ticket";
    }

}
