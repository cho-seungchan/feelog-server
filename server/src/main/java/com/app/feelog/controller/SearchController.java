package com.app.feelog.controller;

import com.app.feelog.domain.dto.DiarySearchDTO;
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

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<DiarySearchDTO> diaries = diaryService.searchDiaries(keyword);
        model.addAttribute("diaries", diaries);
        model.addAttribute("keyword", keyword); // 화면에서 검색어 표시용
        return "search/search"; // search.html
    }
}
