package com.app.feelog.controller;

import com.app.feelog.domain.dto.ChannelPostSearchDTO;
import com.app.feelog.domain.dto.ChannelSearchDTO;
import com.app.feelog.domain.dto.DiarySearchDTO;
import com.app.feelog.service.ChannelPostService;
import com.app.feelog.service.ChannelService;
import com.app.feelog.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
@Slf4j
public class SearchController {

    private final DiaryService diaryService;
    private final ChannelPostService channelPostService;
    private final ChannelService channelService;

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<DiarySearchDTO> diaries = diaryService.searchDiaries(keyword);
        List<ChannelPostSearchDTO> posts = channelPostService.searchChannelPosts(keyword);
        List<ChannelSearchDTO> channels = channelService.searchChannels(keyword);

        model.addAttribute("diaries", diaries);
        model.addAttribute("channelPosts", posts);
        model.addAttribute("channels", channels);
        model.addAttribute("keyword", keyword);

        return "search/search";
    }

}
