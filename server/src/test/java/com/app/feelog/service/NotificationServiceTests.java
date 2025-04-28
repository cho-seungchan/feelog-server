package com.app.feelog.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class NotificationServiceTests {

    @Autowired
    private NotificationService notificationService;

    @Test
    void communityPostNotificationTest() {
        Long senderId = 1L;
        Long receiverId = 2L;
        Long communityPostId = 48L;

        if (!senderId.equals(receiverId)) {
            notificationService.sendCommunityPostNotification(senderId, receiverId, communityPostId);
        }

        log.info("커뮤니티 글 알림 테스트 완료");
    }

    @Test
    void postReplyNotificationTest() {
        Long senderId = 2L;
        Long receiverId = 1L;
        Long postReplyId = 1L;

        if (!senderId.equals(receiverId)) {
            notificationService.sendPostReplyNotification(senderId, receiverId, postReplyId);
        }

        log.info("포스트 댓글 알림 테스트 완료");
    }

    @Test
    void postReplyLikeNotificationTest() {
        Long senderId = 3L;
        Long receiverId = 2L;
        Long postReplyLikeId = 1L;

        if (!senderId.equals(receiverId)) {
            notificationService.sendPostReplyLikeNotification(senderId, receiverId, postReplyLikeId);
        }

        log.info("포스트 댓글 좋아요 알림 테스트 완료");
    }

    @Test
    void postLikeNotificationTest() {
        Long senderId = 1L;
        Long receiverId = 2L;
        Long postLikeId = 2L;

        if (!senderId.equals(receiverId)) {
            notificationService.sendPostLikeNotification(senderId, receiverId, postLikeId);
        }

        log.info("포스트 좋아요 알림 테스트 완료");
    }


    @Test
    void receiveMessageNotificationTest() {
        Long senderId = 1L;
        Long receiverId = 2L;
        Long receiveMessageId = 1L;

        if (!senderId.equals(receiverId)) {
            notificationService.sendReceiveMessageNotification(senderId, receiverId, receiveMessageId);
        }
        log.info("수신 메시지 알림 insert 테스트 완료!");
    }

}
