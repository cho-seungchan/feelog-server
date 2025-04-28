package com.app.feelog.controller;

import com.app.feelog.domain.dto.*;
import com.app.feelog.domain.vo.FileVO;
import com.app.feelog.repository.FileDAO;
import com.app.feelog.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
@Slf4j
public class MainController {

    private final DiaryService diaryService;
    private final DiaryFileServiceImpl diaryFileServiceImpl;
    private final TagServiceImpl tagServiceImpl;
    private final HttpSession session;
    private final DiaryDTO diaryDTO;
    private final ChallengeDiaryService challengeDiaryService;
    private final ChannelPostService channelPostService;
    private final DiaryFileService diaryFileService;
    private final FileService fileService;
    private final FileDAO fileDAO;
    private final ChannelPostFileService channelPostFileService;
    private final NotificationService notificationService;
    private final FaqService faqService;
    private final EmailServiceJk emailServiceJk;

    @GetMapping("/cs")
    public String getMainCs() {
        return "main/cs";
    }

    @GetMapping("/intro")
    public String getMainIntro() {
        return "main/intro";
    }

    @GetMapping("/mind-log")
    public String getMainMindLog(Model model, @RequestParam(required = false) Long challengeId,
                                 @SessionAttribute(value = "member", required = false) MemberDTO member) {

        if (member == null) {
            return "login/login";
        }

        DiaryDTO diaryDTO = new DiaryDTO();
        if (challengeId != null) {
            diaryDTO.setChallengeId(challengeId); // 챌린지 아이디 주입
        }

        model.addAttribute("diary", diaryDTO);
        return "main/mind-log";
    }

    @PostMapping("/mind-log")
    public String writeDiary(
            DiaryDTO diaryDTO,
            @RequestParam("thumbnail") MultipartFile thumbnailFile,
            @RequestParam(value = "fileIds", required = false) List<Long> fileIds,
            @RequestParam(value = "tags", required = false) List<String> tags,
            @SessionAttribute(value = "member", required = false) MemberDTO member) {

        Long memberId = member.getId();

        diaryDTO.setMemberId(memberId);
        diaryDTO.setFeelId(1L);
        diaryDTO.setTags(tags);
        diaryDTO.setFileIds(fileIds);

        if (thumbnailFile != null && !thumbnailFile.isEmpty()) {
            FileVO fileVO = fileService.upload(thumbnailFile);
            diaryDTO.setDiaryFilePath(fileVO.getFilePath());
            diaryDTO.setDiaryFileName(fileVO.getFileName());
            diaryDTO.setDiaryFileSize(fileVO.getFileSize());
        }

        diaryService.writeDiary(diaryDTO);

        if (diaryDTO.getChallengeId() != null) {
            ChallengeDiaryDTO challengeDiaryDTO = new ChallengeDiaryDTO();
            challengeDiaryDTO.setId(diaryDTO.getId()); // diaryDTO에서 id 얻어옴
            challengeDiaryDTO.setChallengeId(diaryDTO.getChallengeId());
            challengeDiaryService.addChallengeDiary(challengeDiaryDTO);
        }

        return "redirect:/";
    }
    @GetMapping("/mind-log/edit/{id}")
    public String editDiaryForm(@PathVariable("id") Long id, Model model) {
        DiaryDTO diaryDTO = diaryService.getDiary(id); // 기존 글 불러오기

        // 연결된 챌린지 ID가 있다면 조회해서 세팅
        ChallengeDiaryDTO challengeDiaryDTO = challengeDiaryService.findById(id);
        if (challengeDiaryDTO != null) {
            diaryDTO.setChallengeId(challengeDiaryDTO.getChallengeId());
        }

        model.addAttribute("diary", diaryDTO);
        model.addAttribute("tags", diaryDTO.getTags()); // 태그도 따로 넘기기
        return "main/mind-log-edit"; // edit.html Thymeleaf 템플릿
    }

    @PostMapping("/diary/image/save")
    @ResponseBody
    public Map<String, Object> saveDiaryImage(@RequestBody FileDTO fileDTO) {
        FileVO fileVO = fileDTO.toVO();
        fileDAO.save(fileVO); // 여기서만 file insert


        Map<String, Object> result = new HashMap<>();
        result.put("fileId", fileVO.getId());
        log.info("fileId = {}", fileVO.getId());
        return result;
    }

    // 수정 폼 제출
    @PostMapping("/mind-log/edit/{id}")
    public String updateDiary(@PathVariable Long id,
                              DiaryDTO diaryDTO,
                              @RequestParam(value = "fileIds", required = false) List<Long> fileIds,
                              @RequestParam(value = "tags", required = false) List<String> tags,
                              @SessionAttribute(value = "member", required = false) MemberDTO member) {

        DiaryDTO original = diaryService.getDiary(id); // 기존 데이터 먼저 불러오기

         Long memberId = member.getId();
        diaryDTO.setMemberId(memberId);
        diaryDTO.setId(id);
        diaryDTO.setFileIds(fileIds);
        diaryDTO.setTags(tags);

        // 기존 연결된 챌린지 ID 유지 (없으면 null)
        diaryDTO.setChallengeId(original.getChallengeId());

        diaryService.updateDiary(diaryDTO);
        return "redirect:/";
    }


    @GetMapping("/new-blow")
    public String getNewBlog() {
        return "main/new-blog";
    }

    // 등록 폼
    @GetMapping("/post")
    public String getPostForm(Model model, @SessionAttribute(value = "member", required = false) MemberDTO member) {

        if (member == null) {
            return "login/login";
        }

        ChannelPostDTO postDTO = new ChannelPostDTO();
        model.addAttribute("post", postDTO);

        return "main/post";
    }

    // 등록 처리
    @PostMapping("/post")
    public String writePost(ChannelPostDTO dto,
                            @RequestParam(value = "fileIds", required = false) List<Long> fileIds,
                            @RequestParam(value = "tags", required = false) List<String> tags,
                            @SessionAttribute(value = "member", required = false) MemberDTO member) {

        Long memberId = member.getId();

        dto.setMemberId(memberId);
        dto.setChannelId(1L);
        dto.setFileIds(fileIds);
        dto.setTags(tags);
        channelPostService.writeChannelPost(dto);
        return "redirect:/";
    }


    @PostMapping("/post/image/save")
    @ResponseBody
    public Map<String, Object> savePostImage(@RequestBody FileDTO fileDTO) {
        FileVO fileVO = fileDTO.toVO();
        fileDAO.save(fileVO); // DB insert만

        Map<String, Object> result = new HashMap<>();
        result.put("fileId", fileVO.getId());
        log.info("[post-image-save] fileId = {}", fileVO.getId());
        return result;
    }

    // 수정 폼
    @GetMapping("/post/edit/{id}")
    public String editPostForm(@PathVariable("id") Long id, Model model) {
        ChannelPostDTO dto = channelPostService.getChannelPost(id);
        model.addAttribute("post", dto);
        model.addAttribute("tags", dto.getTags());
        return "main/post-edit"; // → post-edit.html
    }



    // 수정 처리
    @PostMapping("/post/edit/{id}")
    public String updatePost(@PathVariable("id") Long id,
                             ChannelPostDTO dto,
                             @RequestParam(value = "fileIds", required = false) List<Long> fileIds,
                             @RequestParam(value = "tags", required = false) List<String> tags,
                             @RequestParam(value = "removedFileNames", required = false) List<String> removedFileNames,
                             @RequestParam(value = "removedTagContents", required = false) List<String> removedTagContents)
    {
        dto.setId(id);
        dto.setMemberId(1L);
        dto.setFileIds(fileIds);
        dto.setTags(tags);
        dto.setRemovedFileNames(removedFileNames);
        dto.setRemovedTagContents(removedTagContents);

        System.out.println("수정 시 받은 postStatus: " + dto.getPostStatus());
        System.out.println("삭제된 이미지 파일명 목록: " + removedFileNames);
        System.out.println("삭제된 태그들: " + removedTagContents);

        channelPostService.updateChannelPost(dto);
        return "redirect:/";
    }

    @GetMapping("/init")
    @ResponseBody
    public Map<String, Object> searchInit() {
        Map<String, Object> result = new HashMap<>();
        result.put("mindLogs", diaryService.getRecentDiaries());
        result.put("channelPosts", channelPostService.getRecentChannelPosts());

        return result;
    }

    @GetMapping("/notifications")
    @ResponseBody
    public List<NotificationResponseDTO> getMyNotifications(@SessionAttribute(value = "member", required = false) MemberDTO member) {

        Long myId = member.getId();
        return notificationService.getNotificationsByReceiver(myId);
    }

    @GetMapping("/notifications/unread-count")
    @ResponseBody
    public int getUnreadNotificationCount(@SessionAttribute(value = "member", required = false) MemberDTO member) {
        Long receiverId = member.getId();
        return notificationService.getUnreadNotificationCount(receiverId);
    }

    @PostMapping("/notifications/mark-all-read")
    @ResponseBody
    public void markAllNotificationsAsRead(@SessionAttribute(value = "member", required = false) MemberDTO member) {
        Long receiverId = member.getId();
        notificationService.markAllAsRead(receiverId);
    }

    // FAQ 목록 페이지
    @GetMapping("/faq")
    public String faqListPage(@RequestParam(value = "query", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("faqs", faqService.searchFaqs(keyword));
        } else {
            model.addAttribute("faqs", faqService.findAll());
        }
        return "main/cs"; // 목록페이지
    }

    // FAQ 상세 페이지
    @GetMapping("/faq/{id}")
    public String faqDetailPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("faq", faqService.findById(id));
        model.addAttribute("recentFaqs", faqService.findRecentFaqs(3));
        return "main/faq";
    }
    // FAQ 검색 목록
    @GetMapping("/faq/list")
    @ResponseBody
    public List<FaqDTO> getFaqList(@RequestParam(value = "query", required = false) String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return faqService.searchFaqs(keyword);
        } else {
            return faqService.findAll();
        }
    }

    @GetMapping("/ticket")
    public String ticketPage(HttpSession session, Model model) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member != null) {
            model.addAttribute("memberEmail", member.getMemberEmail());
        }

        return "main/ticket";
    }

    @PostMapping("/ticket")
    public String submitTicket(@RequestParam("request[custom_fields][900008472883]") String email,
                               @RequestParam("request[subject]") String subject,
                               @RequestParam("request[description]") String description) {
        emailServiceJk.sendTicketEmail(email, subject, description);
        return "redirect:/main/faq";
    }

}
