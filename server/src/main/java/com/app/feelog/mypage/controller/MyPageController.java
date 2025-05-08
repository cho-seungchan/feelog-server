// 2025.04.17 조승찬  :: 마이페이지

package com.app.feelog.mypage.controller;

import com.app.feelog.domain.dto.ChannelDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.SubscribeDTO;
import com.app.feelog.domain.vo.DiaryVO;
import com.app.feelog.mypage.dto.*;
import com.app.feelog.mypage.service.MyPageService;
import com.app.feelog.util.SixRowPagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myPage")
@Slf4j
public class MyPageController {
    private final HttpSession session;
    private final HttpServletRequest request;
    private final MyPageService myPageService;

    // 2025.04.21  조승찬 :: 프로필 조회
    @GetMapping("/setting-profile")
    public String getSettingProfile(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 멤버 정보 가져오기
        MemberDTO memberDTO = myPageService.getMemberById(member.getId());
        model.addAttribute("member", memberDTO);

        return "myPage/setting-profile";

    }

    // 2025.04.21  조승찬 :: 프로필 수정
    @PostMapping("/setting-profile")
    public String postSettingProfile(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                     MemberDTO memberDTO, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 2025.04.23 조승찬 :: 닉네임 중복 체크
        Optional<MemberDTO> memberOptional = myPageService.getMemberByNickname(memberDTO.getMemberNickname());
        if (memberOptional.isPresent() && !memberOptional.get().getId().equals(member.getId())) {
            model.addAttribute("errorMessage", "중복된 Nickname 입니다.");
            model.addAttribute("member", memberDTO);
            return "myPage/setting-profile";
        }

        memberDTO.setId(member.getId());
        myPageService.postSettingProfile(memberDTO);

        return "redirect:/myPage/setting-profile";

    }

    // 2025.04.22 조승찬 :: 알림 설정 조회
    @GetMapping("/setting-notify")
    public String getSettingNotify(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                   Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 멤버 정보 가져오기
        MemberDTO memberDTO = myPageService.getMemberById(member.getId());
        model.addAttribute("member", memberDTO);

        return "myPage/setting-notify";
    }


    // 2025.04.22 조승찬 :: 알림 설정 수정
    @PostMapping("/setting-notify")
    public String PostSettingNotify(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    MemberDTO memberDTO) {
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        log.info(memberDTO.toString());
        // 화면에서 한글로 올라온 데이타를 컨퍼터가 이넘 객체로 변환 (** dto 생성자에서 default를 off로 set)
        memberDTO.setId(member.getId());
        myPageService.postSettingNotify(memberDTO);

        return "redirect:/myPage/setting-notify";

    }

    ;

    // 2025.04.22 조승찬 :: 채널 만들기 화면
    @GetMapping("/make-channel")
    public String getMakingChannel(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                   Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        ChannelDTO channelDTO = new ChannelDTO();
        model.addAttribute("channel", channelDTO);

        return "myPage/make-channel";
    }

    // 2025.04.22 조승찬 :: 채널 만들기
    @PostMapping("/make-channel")
    public String PostMakingChannel(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    ChannelDTO channelDTO, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 중복 여부 확인. url만 존재하면 중복
        if (myPageService.getChannelByUrl(channelDTO.getChannelUrl()).isPresent()) {
            model.addAttribute("errorMessage", "중복된 url 입니다.");
            model.addAttribute("channel", channelDTO);
            return "myPage/make-channel";
        }

        // 신규 채널 생성
        channelDTO.setMemberId(member.getId());
        myPageService.postMakingChannel(channelDTO);

        return "redirect:/myPage/read-channel/" + channelDTO.getChannelUrl();
    }

    // 2025.04.22 조승찬 :: 채널 보기(수정, 삭제용)
    @GetMapping("/read-channel/{channelUrl}")
    public String getReadChannel(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                 @PathVariable String channelUrl,
                                 Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        ChannelDTO channel = myPageService.getChannelByUrl(channelUrl)
                .orElse(null);

        if (member.getId() != channel.getMemberId()) {
            return "error/error-page";
        }
        model.addAttribute("channel", channel);

        return "myPage/read-channel";
    }

    // 2025.04.23 조승찬 :: 채널 수정
    @PostMapping("/update-channel")
    public String postUpdateChannel(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    ChannelDTO channelDTO, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 중복 여부 확인. id가 다르고 url이 같으면 중복 오류
        Optional<ChannelDTO> channelOptional = myPageService.getChannelByUrl(channelDTO.getChannelUrl());
        if (channelOptional.isPresent() && !channelOptional.get().getId().equals(channelDTO.getId())) {
            model.addAttribute("errorMessage", "중복된 url 입니다.");
            model.addAttribute("channel", channelDTO);
            return "myPage/read-channel";
        }

        // 채널 정보 수정
        myPageService.postUpdateChannel(channelDTO);

        return "redirect:/myPage/read-channel/" + channelDTO.getChannelUrl();
    }

    // 2025.04.23 조승찬 :: 채널 삭제
    @PostMapping("/delete-channel")
    public String postDeleteChannel(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    ChannelDTO channelDTO, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 채널 정보 삭제
        myPageService.postDeleteChannel(channelDTO.getId());

        // 채널 만들기 화면으로 보내기
        ChannelDTO channel = new ChannelDTO();
        model.addAttribute("channel", channel);

        return "myPage/make-channel";
    }

    // 2025.04.23 조승찬 :: 알림 메뉴 중 커뮤니티 목록
    @GetMapping("/notify-community-list")
    public String getNotifyCommunityList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                         Model model, SixRowPagination pagination) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 목록 가져와서 보여주기
        List<NotifyCommunityListDTO> communities = myPageService.getNotifyCommunityList(member.getId(), pagination);
        model.addAttribute("communities", communities);
        model.addAttribute("pagination", pagination);

        log.info(pagination.toString());

        return "myPage/notify-community-list";
    }


    // 2025.04.23 조승찬 :: 알림 메뉴 중 포스트 댓글 목록
    @GetMapping("/notify-reply-list")
    public String getNotifyReplyList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                     Model model, SixRowPagination pagination) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 목록 가져와서 보여주기
        List<NotifyReplyListDTO> replies = myPageService.getNotifyReplyList(member.getId(), pagination);
        model.addAttribute("replies", replies);
        model.addAttribute("pagination", pagination);

        return "myPage/notify-reply-list";
    }

    // 2025.04.24 조승찬 :: 알림 메뉴 중 관리자 알림 목록 :: 당일 일기에 정신점수가 낮으면 서울시 정신건강 복지센터 데이터
    @GetMapping("/notify-admin-list")
    public String getNotifyAdminList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                     Model model, SixRowPagination pagination) throws IOException {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 오늘 작성한 일기의 점수 확인 => 점수가 50점 미만이면 시설 소개하기
        DiaryVO diary = myPageService.getDiaryByMemberId(member.getId()).orElse(null);

        // 50점 미만이면 시설 정보 가져오기
        List<NotifyAdminListDTO> admins = new ArrayList<>();
        if (diary != null && diary.getDiaryScore() < 50) {
            admins = myPageService.getFacilityInfo();
        }

        model.addAttribute("admins", admins);
        model.addAttribute("pagination", pagination);

        return "myPage/notify-admin-list";
    }

    // 2025.04.24 조승찬 :: 구독자 리스트 조회
    @GetMapping("/notify-subscribe")
    public String getNotifySubscribe(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                     Model model, SixRowPagination pagination) {
        log.info("들어옴noti");
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 구독자 리스트 가져오기
        List<NotifySubscribeListDTO> subscribes = myPageService.getNotifySubscribe(member.getId(), pagination);

        model.addAttribute("subscribes", subscribes);
        model.addAttribute("pagination", pagination);
        log.info("나감noti");

        return "myPage/notify-subscribe";
    }

    // 2025.04.24 조승찬 :: 구독자 취소
    @PostMapping("/notify-cancel-subscribe")
    public String cancelNotifySubscribe(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                        SubscribeDTO subscribeDTO, SixRowPagination pagination) {

        log.info("취소처리 :: " + subscribeDTO.toString());
        // 채널 정보 가져오기
        ChannelDTO channel = myPageService.getChannelByMemberId(member.getId()).orElse(null);

        // 취소 처리
        subscribeDTO.setChannelId(channel.getId());
        myPageService.cancelSubscribe(subscribeDTO);

        return "redirect:/myPage/notify-subscribe?page=" + pagination.getPage();
    }

    // 2025.04.24 조승찬 :: 구독 리스트 조회
    @GetMapping("/storage-subscribe")
    public String getStorageSubscribe(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                      Model model, SixRowPagination pagination) {
        log.info("들어옴");
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }
        log.info("로그인멤버 통과");
        // 구독 리스트 가져오기
        List<StorageSubscribeListDTO> subscribes = myPageService.getStorageSubscribe(member.getId(), pagination);
        log.info("subscribes {}", subscribes);
        log.info("pagination {}", pagination);
        model.addAttribute("subscribes", subscribes);
        model.addAttribute("pagination", pagination);
        return "myPage/storage-subscribe";
    }

    // 2025.04.24 조승찬 :: 구독 취소
    @PostMapping("/storage-cancel-subscribe")
    public String cancelStorageSubscribe(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                         SubscribeDTO subscribeDTO, Model model, SixRowPagination pagination) {

        // 취소 처리
        subscribeDTO.setMemberId(member.getId());
        myPageService.cancelSubscribe(subscribeDTO);

        return "redirect:/myPage/storage-subscribe?page=" + pagination.getPage();
    }

    // 2025.04.25 조승찬 :: 스크랩 목록 보기
    @GetMapping("/storage-scrap")
    public String getStorageScrap(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                  Model model, SixRowPagination pagination) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 스크랩 목록 가져오기
        List<StorageScrapListDTO> scraps = myPageService.getStorageScrap(member.getId(), pagination);

        model.addAttribute("scraps", scraps);
        model.addAttribute("pagination", pagination);

        return "myPage/storage-scrap";
    }

    // 2025.04.25 조승찬 :: 스크랩 취소
    @ResponseBody
    @PostMapping("/storage-off-scrap")
    public void storageOffScrap(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                @RequestParam Long id) {

        myPageService.storageOffScrap(id);
    }

    ;

    // 2025.04.25 조승찬 :: 스크랩 재설정
    @ResponseBody
    @PostMapping("/storage-on-scrap")
    public void storageOnScrap(@SessionAttribute(name = "member", required = false) MemberDTO member,
                               @RequestParam Long id) {

        myPageService.storageOnScrap(id);
    }

    ;

    // 2025.04.25 조승찬 :: 좋아요 목록
    @GetMapping("/storage-like")
    public String storageLike(@SessionAttribute(name = "member", required = false) MemberDTO member,
                              Model model, SixRowPagination pagination) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 좋아요 목록 가져오기
        List<StorageLikeListDTO> likes = myPageService.getStorageLike(member.getId(), pagination);

        model.addAttribute("likes", likes);
        model.addAttribute("pagination", pagination);

        return "myPage/storage-like";
    }


    // 2025.04.25 조승찬 :: 좋아요 취소
    @ResponseBody
    @PostMapping("/storage-off-like")
    public ResponseEntity<Map<String, Object>> storageOffLike(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                                              @RequestParam Long id, @RequestParam Long postId) {

        myPageService.storageOffLike(id);

        Map<String, Object> response = new HashMap<String, Object>();
        int likeCount = myPageService.getLikeCount(postId);
        response.put("likeCount", likeCount);
        return ResponseEntity.ok(response);
    }

    ;

    // 2025.04.25 조승찬 :: 좋아요 재설정
    @ResponseBody
    @PostMapping("/storage-on-like")
    public ResponseEntity<Map<String, Object>> storageOnLike(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                                             @RequestParam Long id, @RequestParam Long postId) {

        myPageService.storageOnLike(id);

        Map<String, Object> response = new HashMap<String, Object>();
        int likeCount = myPageService.getLikeCount(postId);
        response.put("likeCount", likeCount);
        return ResponseEntity.ok(response);
    }

    ;

    // 2025.04.25  조승찬 :: 댓글 목록
    @GetMapping("/storage-reply")
    public String getStorageReply(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                  Model model, SixRowPagination pagination) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 댓글 목록 가져오기
        List<StorageReplyListDTO> replies = myPageService.getStorageReply(member.getId(), pagination);

        model.addAttribute("replies", replies);
        model.addAttribute("pagination", pagination);

        return "myPage/storage-reply";
    }

    // 2025.04.25 조승찬 :: 댓글 삭제
    @PostMapping("/storage-delete-reply")
    public String deleteStorageReply(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                     @RequestParam Long id, SixRowPagination pagination) {

        // 삭제 처리
        myPageService.deleteStorageReply(id);

        return "redirect:/myPage/storage-reply?page=" + pagination.getPage();
    }


}
