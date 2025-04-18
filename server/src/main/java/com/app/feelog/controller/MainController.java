package com.app.feelog.controller;

import com.app.feelog.domain.dto.DiaryDTO;
import com.app.feelog.domain.dto.DiaryFileDTO;
import com.app.feelog.domain.dto.DiaryTagDTO;
import com.app.feelog.domain.dto.TagDTO;
import com.app.feelog.domain.enumeration.TagStatus;
import com.app.feelog.domain.vo.TagVO;
import com.app.feelog.service.DiaryFileService;
import com.app.feelog.service.DiaryService;
import com.app.feelog.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
@Slf4j
public class MainController {

    private final DiaryService diaryService;
    private final DiaryFileService diaryFileService;
    private final TagService tagService;

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

        diaryDTO.setMemberId(1L); // 테스트용 하드코딩
        diaryDTO.setFeelId(1L);   // 테스트용 하드코딩

        // 1. 다이어리 저장
        Long diaryId = diaryService.writeDiary(diaryDTO);

        // 2. 첨부 이미지 저장
        if (fileIds != null && !fileIds.isEmpty()) {
            for (Long fileId : fileIds) {
                DiaryFileDTO diaryFileDTO = new DiaryFileDTO();
                diaryFileDTO.setDiaryId(diaryId);
                diaryFileDTO.setFileId(fileId);
                diaryFileService.addDiaryFile(diaryFileDTO);
            }
        }

        // 3. 태그 저장 로직
        if (tags != null && !tags.isEmpty()) {
            for (String content : tags) {
                TagVO tagVO = tagService.findTagsByContents(content)
                        .stream()
                        .findFirst()
                        .orElseGet(() -> {
                            TagDTO newTag = new TagDTO();
                            newTag.setContents(content);
                            tagService.saveTag(newTag);

                            // 여기서 다시 id 포함된 VO로 조회
                            return tagService.findTagsByContents(content)
                                    .stream()
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException("태그 저장 실패: " + content));
                        });

                DiaryTagDTO diaryTagDTO = new DiaryTagDTO();
                diaryTagDTO.setDiaryId(diaryId);
                diaryTagDTO.setTagId(tagVO.getId());

                tagService.saveDiaryTag(diaryTagDTO);
            }
        }

        return "redirect:/";
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
