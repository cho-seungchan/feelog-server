// 2025.04.17 조승찬  :: 마이페이지

package com.app.feelog.mypage.controller;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.mypage.service.MyPageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
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

        return "mypage/setting-profile";

    }

    // 2025.04.21  조승찬 :: 프로필 수정
    @PostMapping("/setting-profile")
    public String postSettingProfile(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                     MemberDTO memberDTO) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        memberDTO.setId(member.getId());
        myPageService.postSettingProfile(memberDTO);

        return "redirect:/mypage/setting-profile";

    }

    // 2025.04.22 조승찬 :: 알림 설정 조회
    @GetMapping("/setting-notify")
    public String settingNotify(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 멤버 정보 가져오기
        MemberDTO memberDTO = myPageService.getMemberById(member.getId());
        model.addAttribute("member", memberDTO);

        return "mypage/setting-notify";
    }

    @GetMapping("/test-profile")
    public String getView(){
        return "mypage/setting-profile";
    }

    @GetMapping("/admin-notice-list")
    public String adminNoticeList(){
        return "mypage/admin-notice-list";
    }

    @GetMapping("/block-list")
    public String blockList(){
        return "mypage/block-list";
    }

    @GetMapping("/community")
    public String community(){
        return "mypage/community";
    }

    @GetMapping("/community-reply")
    public String communityReply(){
        return "mypage/community-reply";
    }

    @GetMapping("/making-channel")
    public String makingChannel(){
        return "mypage/making-channel";
    }

    @GetMapping("/message-list")
    public String messageList(){
        return "mypage/message-list";
    }

    @GetMapping("/notify-admin-list")
    public String notifyAdminList(){
        return "mypage/notify-admin-list";
    }

    @GetMapping("/notify-list")
    public String notifyList(){
        return "mypage/notify-list";
    }

    @GetMapping("/storage-reply")
    public String storageReply(){
        return "mypage/storage-reply";
    }

    @GetMapping("/storage-scrab")
    public String storageScrab(){
        return "mypage/storage-scrab";
    }

    @GetMapping("/subscribe-list")
    public String subscribeList(){
        return "mypage/subscribe-list";
    }

}
