package com.app.feelog.controller;

import com.app.feelog.domain.dto.*;
import com.app.feelog.domain.dto.joinDTO.DiaryDetailDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryJoinDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryPaginationDTO;
import com.app.feelog.domain.enumeration.DiaryOpen;
import com.app.feelog.service.DiaryService;
import com.app.feelog.service.SubscribeService;
import com.app.feelog.service.voToDto.LikeService;
import com.app.feelog.service.voToDto.ReplyService;
import com.app.feelog.service.voToDto.ReportService;
import com.app.feelog.util.pagination.PostPagination;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/diary")
@RequiredArgsConstructor
@Slf4j
public class DiaryController {
    private final DiaryService diaryService;
    private final ReportService reportService;
    private final HttpSession session;
    private final SubscribeService subscribeService;
    private final ReplyService replyService;
    private final LikeService likeService;

    @GetMapping("/all-diary")
    public void goToAllDiary(){};

    @GetMapping("/diary-read")
    public String goToDiaryRead(Model model, @RequestParam("id") Long diaryId){
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        DiaryDetailDTO diary = diaryService.getDiaryDetailByDiaryId(diaryId);

        if(loginMember != null){
            List<Long> diaryLikeIds = likeService.getDiaryLikeIdsByMemberId(loginMember.getId());

            diary.setLiked(diaryLikeIds.contains(diaryId));
        }

        model.addAttribute("diary", diary);

        diaryService.addDiaryReadCount(diaryId);

        return "/diary/diary-read";
    };

    @GetMapping("/my-diary")
    public void goToMyDiary(){
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
    };

    @GetMapping("diaryListClose")
    @ResponseBody
    public DiaryPaginationDTO getDiaryList(PostPagination postPagination){
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
        DiaryPaginationDTO diaryPaginationDTO = diaryService.getDiaryList(postPagination);

        if(loginMember != null){
                List<Long> likeIds = likeService.getDiaryLikeIdsByMemberId(loginMember.getId());
                Set<Long> likeIdSet = new HashSet<>(likeIds);

                diaryPaginationDTO.getDiaryList().forEach(diary -> {
                    diary.setLiked(likeIdSet.contains(diary.getId()));
                });
        }

        return diaryPaginationDTO;
    }

    @GetMapping("/diaryListAllAndSubscribe")
    @ResponseBody
    public DiaryPaginationDTO getDiaryListAllAndSubscribe(PostPagination postPagination){
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
        DiaryPaginationDTO diaryPaginationDTO = new DiaryPaginationDTO();

        if(loginMember != null){
            diaryPaginationDTO = diaryService.getDiaryListAllAndSubscribe(postPagination, loginMember.getId());

            List<Long> likeIds = likeService.getDiaryLikeIdsByMemberId(loginMember.getId());
            Set<Long> likeIdSet = new HashSet<>(likeIds);

            diaryPaginationDTO.getDiaryList().forEach(diary -> {
                diary.setLiked(likeIdSet.contains(diary.getId()));
            });
        } else {
            diaryPaginationDTO = diaryService.getDiaryListAll(postPagination);
            log.info("diary : {}",diaryPaginationDTO);
        }

        return diaryPaginationDTO;
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

    @GetMapping("/randomDiaryList")
    @ResponseBody
    public List<DiaryJoinDTO> diaryRandomList(){
        return  diaryService.getDiaryRandom();
    }

    @PostMapping("/insertDiaryReply")
    public ResponseEntity<Void> insertReply(@RequestBody DiaryReplyDTO diaryReplyDTO, HttpServletResponse response) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        if (loginMember == null) {
            try {
                response.sendRedirect("/login/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        replyService.addDiaryReply(diaryReplyDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/replyLists")
    @ResponseBody
    public List<DiaryReplyDTO> getReplyLists(Long diaryId){
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
        List<DiaryReplyDTO> diaryReplyList = replyService.getDiaryReplyByDiaryId(diaryId);

        if(loginMember !=null){
            List<Long> replyIds = likeService.getLikeIdsByMemberId(loginMember.getId());
            Set<Long> replyIdSet = new HashSet<>(replyIds);

            diaryReplyList.forEach(reply -> {reply.setLiked(replyIdSet.contains(reply.getId()));});
        }

        diaryReplyList.forEach(diaryReplyDTO -> {
            diaryReplyDTO.setReplyLikeCount(likeService.getDiaryReplyLikeCount(diaryReplyDTO.getId()));
        });

        return diaryReplyList;
    }

    @PostMapping("insertDiaryReplyLike")
    public void insertDiaryReplyLike(@RequestBody DiaryReplyLikeDTO diaryReplyDTO) {
        likeService.insertDiaryReplyLike(diaryReplyDTO);
    }

    @GetMapping("/diaryReplyReportList")
    @ResponseBody
    public List<DiaryReplyReportDTO> getDiaryReplyReportList(Long memberId){
        return reportService.getDiaryReplyReportListByMemberId(memberId);
    }

    @PostMapping("/addDiaryReplyReport")
    public void addDiaryReplyReport(@RequestBody DiaryReplyReportDTO diaryReplyReportDTO){
        log.info(diaryReplyReportDTO.toString());
        reportService.addDiaryReplyReport(diaryReplyReportDTO);
    }

    @PostMapping("/addOrDeleteDiaryLike")
    public void addOrDeleteDiaryLike(@RequestBody DiaryLikeDTO diaryLikeDTO){
        likeService.addOrDeleteDiaryLike(diaryLikeDTO);
    }
}
