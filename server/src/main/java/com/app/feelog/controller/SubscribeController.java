package com.app.feelog.controller;

import com.app.feelog.domain.dto.ChannelSearchDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.service.ChannelService;
import com.app.feelog.service.SubscribeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/subscribe")
public class SubscribeController {

    private final SubscribeService subscribeService;
    private final ChannelService channelService;

    @PostMapping("/{channelId}")
    public ResponseEntity<String> toggleSubscribe(@PathVariable("channelId") Long channelId,
                                                  @SessionAttribute(value = "member", required = false) MemberDTO member) {
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        Long memberId = member.getId();
        Long channelOwnerId = channelService.findChannelOwnerId(channelId);

        if (memberId.equals(channelOwnerId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("자기 채널은 구독할 수 없습니다.");
        }

        boolean isSubscribed = subscribeService.isSubscribed(memberId, channelId);

        if (isSubscribed) {
            subscribeService.unsubscribe(memberId, channelId);
            return ResponseEntity.ok("구독 취소");
        } else {
            subscribeService.subscribe(memberId, channelId);
            return ResponseEntity.ok("구독 완료");
        }
    }

}
