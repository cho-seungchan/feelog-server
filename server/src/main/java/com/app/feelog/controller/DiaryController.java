package com.app.feelog.controller;

import com.app.feelog.domain.dto.DiaryReportDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryPaginationDTO;
import com.app.feelog.service.DiaryService;
import com.app.feelog.service.voToDto.ReportService;
import com.app.feelog.util.pagination.PostPagination;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/diary")
@RequiredArgsConstructor
@Slf4j
public class DiaryController {
    private final DiaryService diaryService;
    private final ReportService reportService;
    private final HttpSession session;

    @GetMapping("/all-diary")
    public void goToAllDiary(){};

    @GetMapping("/diary-read")
    public void goToDiaryRead(){};

    @GetMapping("/my-diary")
    public void goToMyDiary(){
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
    };

    @GetMapping("diaryList")
    @ResponseBody
    public DiaryPaginationDTO getDiaryList(PostPagination postPagination){
        return diaryService.getDiaryList(postPagination);
    }

    @GetMapping("/diaryReportIds")
    @ResponseBody
    public List<Long> getDiaryReportIds(Long memberId){
        return diaryService.getDiaryReportIds(memberId);
    }

    @PostMapping("/insertDiaryReport")
    public void addDiaryReport(@RequestBody DiaryReportDTO diaryReportDTO){
        log.info(diaryReportDTO.toString());
        reportService.addDiaryReport(diaryReportDTO);
    }
}
