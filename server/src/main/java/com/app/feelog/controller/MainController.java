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

import java.util.*;

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
    private final ChannelService channelService;
    private final DiaryScoreService diaryScoreService;

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
    public String editDiaryForm(@PathVariable("id") Long id, Model model,
                                @SessionAttribute(value = "member", required = false) MemberDTO member) {

        if (member == null) {
            return "login/login";
        }

        // 기존 글 불러오기
        DiaryDTO diaryDTO = diaryService.getDiary(id);

        // 본인 글이 아닐 경우 접근 차단 (선택적 처리)
        if (!member.getId().equals(diaryDTO.getMemberId())) {
            return "/error/error-page"; // 접근 권한 없음 페이지 등으로 리디렉션 가능
        }

        log.info("diaryDTO: " + diaryDTO);

        // 연결된 챌린지 ID가 있다면 조회해서 세팅
        ChallengeDiaryDTO challengeDiaryDTO = challengeDiaryService.findById(id);
        if (challengeDiaryDTO != null) {
            diaryDTO.setChallengeId(challengeDiaryDTO.getChallengeId());
        }

        model.addAttribute("diary", diaryDTO);
        model.addAttribute("tags", diaryDTO.getTags());

        return "main/mind-log-edit";
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
//    @PostMapping("/post")
//    public String writePost(ChannelPostDTO dto,
//                            @RequestParam(value = "fileIds", required = false) List<Long> fileIds,
//                            @RequestParam(value = "tags", required = false) List<String> tags,
//                            @SessionAttribute(value = "member", required = false) MemberDTO member) {
//
//        Long memberId = member.getId();
//
//        dto.setMemberId(memberId);
//        dto.setChannelId(1L);
//        dto.setFileIds(fileIds);
//        dto.setTags(tags);
//        channelPostService.writeChannelPost(dto);
//        return "redirect:/";
//    }
    // 등록 처리
    @PostMapping("/post")
    public String writePost(ChannelPostDTO dto,
                            @RequestParam(value = "fileIds", required = false) List<Long> fileIds,
                            @RequestParam(value = "tags", required = false) List<String> tags,
                            @SessionAttribute(value = "member", required = false) MemberDTO member) {

        Long memberId = member.getId();
        dto.setMemberId(memberId);

        // Service를 통해 내 채널 정보 조회
        Optional<ChannelDTO> optionalChannelDTO = channelService.getMyChannel(memberId);

        // 조회된 채널이 없으면 예외 처리
        ChannelDTO channelDTO = optionalChannelDTO.orElseThrow(() ->
                new IllegalArgumentException("해당 멤버가 소속된 채널이 없습니다.")
        );

        // DTO에 채널 ID 설정
        dto.setChannelId(channelDTO.getId());

        dto.setFileIds(fileIds);
        dto.setTags(tags);

        // 포스트 등록 실행
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
    public List<NotificationResponseDTO> getMyNotifications(
            @SessionAttribute(value = "member", required = false) MemberDTO member) {

        if (member == null) {
            return Collections.emptyList(); // 로그인 안 한 경우 빈 리스트 반환
        }

        Long myId = member.getId();
        return notificationService.getNotificationsByReceiver(myId);
    }

    @GetMapping("/notifications/unread-count")
    @ResponseBody
    public int getUnreadNotificationCount(
            @SessionAttribute(value = "member", required = false) MemberDTO member) {

        if (member == null) {
            return 0; // 로그인 안 한 경우 안 읽은 알림 없음
        }

        Long receiverId = member.getId();
        return notificationService.getUnreadNotificationCount(receiverId);
    }

    @PostMapping("/notifications/mark-all-read")
    @ResponseBody
    public void markAllNotificationsAsRead(@SessionAttribute(value = "member", required = false) MemberDTO member) {
        if (member == null) {
            return; // 로그인 안 되어 있으면 아무 작업도 하지 않음
        }

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


    @GetMapping("/channel/my")
    @ResponseBody
    public ResponseEntity<?> getMyChannel(HttpSession session) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
        if (loginMember == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        Optional<ChannelDTO> optionalChannel = channelService.getMyChannel(loginMember.getId());

        if (optionalChannel.isPresent()) {
            return ResponseEntity.ok(optionalChannel.get());
        } else {
            return ResponseEntity.ok(null);
        }
    }


    @PostMapping("/api/feeling-check")
    @ResponseBody
    public ResponseEntity<?> checkFeeling(@RequestBody FeelingRequestDTO request) {
        String contents = request.getContents();

        log.info(contents);

        // 1. 점수 산정
        int score = diaryScoreService.getEmotionScore(contents);
        log.info("감정 분석 결과 점수: {}", score); // 로그 추가

        // 2. 점수로 정보 조회
        Optional<DiaryScoreDTO> scoreDTO = diaryScoreService.getScoreById((long) score);
        if (scoreDTO.isEmpty()) {
            log.warn("감정 점수 {}에 해당하는 정보 없음", score);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Score info not found.");
        }

        // 3. 응답 구성
        DiaryScoreDTO dto = scoreDTO.get();
        Map<String, Object> result = new HashMap<>();
        result.put("score", score); //score 그대로 유지 (프론트 연동용)
        result.put("id", dto.getId()); // 필요하면 이거도 명시적으로 전달
        result.put("message", dto.getScoreMessage());
        result.put("imgUrl", "/files/display?path=" + dto.getScoreFilePath() + "/" + dto.getScoreFileName());

        log.info("감정 분석 결과 응답: {}", result); //최종 응답 로그

        return ResponseEntity.ok(result);
    }

    @GetMapping("/feeling-score/{id}")
    @ResponseBody
    public ResponseEntity<?> getFeelingScore(@PathVariable("id") Long id) {
        Optional<DiaryScoreDTO> optionalScore = diaryScoreService.getScoreById(id);

        return optionalScore
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 감정 점수가 존재하지 않습니다."));
    }

}
