package com.app.feelog.controller;

import com.app.feelog.service.SubscribeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscribe")
public class SubscribeController {

    private final SubscribeService subscribeService;

    @PostMapping("/{channelId}")
    public ResponseEntity<String> toggleSubscribe(@PathVariable("channelId") Long channelId,
                                                  HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginId");

        if (memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }

        Long channelOwnerId = subscribeService.findChannelOwnerId(channelId);
        if (memberId.equals(channelOwnerId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("자기 채널은 구독할 수 없습니다");
        }

        boolean subscribed = subscribeService.toggleSubscribe(memberId, channelId);
        return ResponseEntity.ok(subscribed ? "subscribed" : "unsubscribed");
    }

}
