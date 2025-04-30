package com.app.feelog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/feelog.com")
@Slf4j
public class BlogController {

    @GetMapping("/blog-challenge")
    public String getBlogChallenge() {
        return "blog/blog-challenge";
    }

    @GetMapping("/blog-home")
    public String getBlogHome() {
        return "blog/blog-home";
    }

    @GetMapping("/blog-mind-log")
    public String getBlogMindLog() {
        return "blog/blog-mind-log";
    }

    @GetMapping("/blog-post")
    public String getBlogPost() {
        return "blog/blog-post";
    }

}
