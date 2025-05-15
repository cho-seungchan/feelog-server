package com.app.feelog.service;

import com.app.feelog.domain.dto.NotificationDTO;
import com.app.feelog.domain.dto.NotificationResponseDTO;
import com.app.feelog.domain.dto.PostLikeNotificationDTO;
import com.app.feelog.domain.vo.NotificationVO;
import com.app.feelog.domain.vo.PostLikeNotificationVO;

import java.util.List;

public interface NotificationService {

    void sendSubscribeNotification(Long senderId, Long channelId, Long subscribeId);

    void sendCommunityPostNotification(Long senderId, Long receiverId, Long communityPostId);

    void sendPostReplyNotification(Long senderId, Long receiverId, Long postReplyId);

    void sendPostReplyLikeNotification(Long senderId, Long receiverId, Long postReplyLikeId);

    void sendPostLikeNotification(Long senderId, Long receiverId, Long postLikeId);

    void sendReceiveMessageNotification(Long senderId, Long receiverId, Long receiveMessageId);

    List<NotificationResponseDTO> getNotificationsByReceiver(Long receiverId);

    int getUnreadNotificationCount(Long receiverId);

    public void markAllAsRead(Long receiverId);

}
