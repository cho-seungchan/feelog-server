package com.app.feelog.controller;

import com.app.feelog.domain.dto.DiaryDTO;
import com.app.feelog.service.DiaryFileServiceImpl;
import com.app.feelog.service.DiaryService;
import com.app.feelog.service.TagServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
@Slf4j
public class MainController {

    private final DiaryService diaryService;
    private final DiaryFileServiceImpl diaryFileServiceImpl;
    private final TagServiceImpl tagServiceImpl;
    private final HttpSession session;

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
    public String getMainMindLog(Model model) {
        model.addAttribute("diary", new DiaryDTO());
        return "main/mind-log";
    }

    @PostMapping("/mind-log")
    public String writeDiary(
            DiaryDTO diaryDTO,
            @RequestParam(value = "fileIds", required = false) List<Long> fileIds,
            @RequestParam(value = "tags", required = false) List<String> tags) {

        // 테스트용 (나중에 세션에서 memberId, feelId 받아오기)
        diaryDTO.setMemberId(1L);
        diaryDTO.setFeelId(1L);
        diaryDTO.setFileIds(fileIds);  // 리스트 주입
        diaryDTO.setTags(tags);        // 리스트 주입

        diaryService.writeDiary(diaryDTO); // 통합 저장

        return "redirect:/";
    }

    @GetMapping("/mind-log/edit/{id}")
    public String editDiaryForm(@PathVariable("id") Long id, Model model) {
        DiaryDTO diaryDTO = diaryService.getDiary(id); // 기존 글 불러오기
        model.addAttribute("diary", diaryDTO);
        model.addAttribute("tags", diaryDTO.getTags()); // 태그도 따로 넘기기
        return "main/mind-log-edit"; // edit.html Thymeleaf 템플릿
    }

    // 수정 폼 제출
    @PostMapping("/mind-log/edit/{id}")
    public String updateDiary(@PathVariable Long id,
                              DiaryDTO diaryDTO,
                              @RequestParam(value = "fileIds", required = false) List<Long> fileIds,
                              @RequestParam(value = "tags", required = false) List<String> tags) {
//        Long memberId = (Long) session.getAttribute("memberId");
        diaryDTO.setMemberId(1L);
        diaryDTO.setId(id);
        diaryDTO.setFileIds(fileIds);
        diaryDTO.setTags(tags);
        diaryService.updateDiary(diaryDTO);
        return "redirect:/";
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
