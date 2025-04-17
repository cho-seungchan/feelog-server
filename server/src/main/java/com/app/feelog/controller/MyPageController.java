// 2025.04.17 조승찬  :: 마이페이지

package com.app.feelog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
@Slf4j
public class MyPageController {

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

    @GetMapping("/setting-notify")
    public String settingNotify(){
        return "mypage/setting-notify";
    }

    @GetMapping("/setting-profile")
    public String settingProfile(){
        return "mypage/setting-profile";
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
