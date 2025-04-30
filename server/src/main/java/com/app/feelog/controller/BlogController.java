package com.app.feelog.controller;

import com.app.feelog.domain.dto.DiaryDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.service.ChannelService;
import com.app.feelog.service.DiaryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/feelog.com")
@Slf4j
public class BlogController {

    private final DiaryService diaryService;
    private final ChannelService channelService;

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

    @GetMapping("/feelog.com/{channelUrl}")
    public ResponseEntity<List<DiaryDTO>> getVisibleDiariesByChannelUrl(
            @PathVariable("channelUrl") String channelUrl,
            HttpSession session
    ) {
        Long viewerId = null;
        MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
        if (loginMember != null) {
            viewerId = loginMember.getId();
        }

        // 채널 URL을 기준으로 채널 주인의 memberId 조회
        Long channelOwnerId = channelService.findMemberIdByChannelUrl(channelUrl);

        if (channelOwnerId == null) {
            return ResponseEntity.notFound().build();
        }

        List<DiaryDTO> diaries = diaryService.findVisibleDiaries(channelOwnerId, viewerId);
        return ResponseEntity.ok(diaries);
    }

}
