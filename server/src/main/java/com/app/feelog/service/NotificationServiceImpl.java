package com.app.feelog.service;

import com.app.feelog.domain.dto.*;
import com.app.feelog.domain.enumeration.NotificationChecked;
import com.app.feelog.domain.enumeration.NotificationType;
import com.app.feelog.domain.vo.NotificationVO;
import com.app.feelog.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationDAO notificationDAO;
    private final ChannelDAO channelDAO;
    private final SubscribeNotificationDAO subscribeNotificationDAO;
    private final CommunityPostNotificationDAO communityPostNotificationDAO;
    private final PostReplyNotificationDAO postReplyNotificationDAO;
    private final PostReplyLikeNotificationDAO postReplyLikeNotificationDAO;
    private final PostLikeNotificationDAO postLikeNotificationDAO;
    private final ReceiveMessageNotificationDAO receiveMessageNotificationDAO;
    private final ChannelService channelService;

    @Override
    @Transactional
    public void sendSubscribeNotification(Long senderId, Long receiverId, Long subscribeId) {

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setSenderId(senderId);
        notificationDTO.setReceiverId(channelService.findChannelOwnerId(receiverId));
        notificationDTO.setNotificationChecked(NotificationChecked.UNREAD);

        NotificationVO notificationVO = notificationDTO.toVO();
        notificationDAO.insert(notificationVO);

        Long newNotificationId = notificationVO.getId();

        SubscribeNotificationDTO subscribeNotificationDTO = new SubscribeNotificationDTO();
        subscribeNotificationDTO.setId(newNotificationId);
        subscribeNotificationDTO.setSubscribeId(subscribeId);
        subscribeNotificationDAO.insert(subscribeNotificationDTO.toVO());
    }


    @Override
    @Transactional
    public void sendCommunityPostNotification(Long senderId, Long receiverId, Long communityPostId) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setSenderId(senderId);
        notificationDTO.setReceiverId(receiverId);
        notificationDTO.setNotificationChecked(NotificationChecked.UNREAD);

        NotificationVO notificationVO = notificationDTO.toVO();
        notificationDAO.insert(notificationVO);

        Long newNotificationId = notificationVO.getId();

        CommunityPostNotificationDTO communityPostNotificationDTO = new CommunityPostNotificationDTO();
        communityPostNotificationDTO.setId(newNotificationId);
        communityPostNotificationDTO.setCommunityPostId(communityPostId);
        communityPostNotificationDAO.insert(communityPostNotificationDTO.toVO());
    }

    @Override
    @Transactional
    public void sendPostReplyNotification(Long senderId, Long receiverId, Long postReplyId) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setSenderId(senderId);
        notificationDTO.setReceiverId(receiverId);
        notificationDTO.setNotificationChecked(NotificationChecked.UNREAD);

        NotificationVO notificationVO = notificationDTO.toVO();
        notificationDAO.insert(notificationVO);

        Long newNotificationId = notificationVO.getId();

        PostReplyNotificationDTO postReplyNotificationDTO = new PostReplyNotificationDTO();
        postReplyNotificationDTO.setId(newNotificationId);
        postReplyNotificationDTO.setPostReplyId(postReplyId);
        postReplyNotificationDAO.insert(postReplyNotificationDTO.toVO());
    }

    @Override
    @Transactional
    public void sendPostReplyLikeNotification(Long senderId, Long receiverId, Long postReplyLikeId) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setSenderId(senderId);
        notificationDTO.setReceiverId(receiverId);
        notificationDTO.setNotificationChecked(NotificationChecked.UNREAD);

        NotificationVO notificationVO = notificationDTO.toVO();
        notificationDAO.insert(notificationVO);

        Long newNotificationId = notificationVO.getId();

        PostReplyLikeNotificationDTO postReplyLikeNotificationDTO = new PostReplyLikeNotificationDTO();
        postReplyLikeNotificationDTO.setId(newNotificationId);
        postReplyLikeNotificationDTO.setPostReplyLikeId(postReplyLikeId);
        postReplyLikeNotificationDAO.insert(postReplyLikeNotificationDTO.toVO());
    }

    @Override
    @Transactional
    public void sendPostLikeNotification(Long senderId, Long receiverId, Long postLikeId) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setSenderId(senderId);
        notificationDTO.setReceiverId(receiverId);
        notificationDTO.setNotificationChecked(NotificationChecked.UNREAD);

        NotificationVO notificationVO = notificationDTO.toVO();
        notificationDAO.insert(notificationVO);

        Long newNotificationId = notificationVO.getId();

        PostLikeNotificationDTO postLikeNotificationDTO = new PostLikeNotificationDTO();
        postLikeNotificationDTO.setId(newNotificationId);
        postLikeNotificationDTO.setPostLikeId(postLikeId);
        log.info("postLike {}", postLikeNotificationDTO);
        postLikeNotificationDAO.insert(postLikeNotificationDTO.toVO());
    }

    @Override
    @Transactional
    public void sendReceiveMessageNotification(Long senderId, Long receiverId, Long receiveMessageId) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setSenderId(senderId);
        notificationDTO.setReceiverId(receiverId);
        notificationDTO.setNotificationChecked(NotificationChecked.UNREAD);

        NotificationVO notificationVO = notificationDTO.toVO();
        notificationDAO.insert(notificationVO);

        Long newNotificationId = notificationVO.getId();

        ReceiveMessageNotificationDTO receiveMessageNotificationDTO = new ReceiveMessageNotificationDTO();
        receiveMessageNotificationDTO.setId(newNotificationId);
        receiveMessageNotificationDTO.setReceiveMessageId(receiveMessageId);
        receiveMessageNotificationDAO.insert(receiveMessageNotificationDTO.toVO());
    }


//    @Override
//    public List<NotificationResponseDTO> getNotificationsByReceiver(Long receiverId) {
//        List<NotificationResponseDTO> notifications = notificationDAO.findAllByReceiverId(receiverId);
//
//        if (notifications == null) {
//            notifications = new ArrayList<>();
//        }
//
//        List<NotificationResponseDTO> filteredNotifications = new ArrayList<>();
//        for (NotificationResponseDTO dto : notifications) {
//            if (dto == null) {
//                continue;
//            }
//
//            // 0. 프로필 기본값 세팅
//            if (dto.getSenderFilePath() == null || dto.getSenderFileName() == null) {
//                dto.setSenderFilePath("/images");
//                dto.setSenderFileName("avatar_blank.png");
//            }
//
//            // 1. 읽음 상태 세팅
//            if (dto.getNotificationChecked() == NotificationChecked.READ) {
//                dto.setNotificationChecked(NotificationChecked.READ);
//            } else {
//                dto.setNotificationChecked(NotificationChecked.UNREAD);
//            }
//
//            // 2. 메세지 서머리 세팅
//            String summary = "새 알림이 도착했어요.";
//
//            NotificationType notificationType = null;
//            if (dto.getNotificationType() != null) {
//                try {
//                    notificationType = NotificationType.valueOf(dto.getNotificationType().toString());
//                } catch (IllegalArgumentException e) {
//
//                }
//            }
//
//            if (notificationType != null) {
//                switch (notificationType) {
//                    case SUBSCRIBE:
//                        summary = dto.getSenderNickname() + "님이 회원님의 채널을 구독했어요.";
//                        break;
//                    case COMMUNITY_POST:
//                        summary = dto.getSenderNickname() + "님의 채널에서 커뮤니티 글이 작성됐어요.";
//                        break;
//                    case POST_REPLY:
//                        summary = dto.getSenderNickname() + "님이 댓글을 남겼어요.";
//                        break;
//                    case POST_REPLY_LIKE:
//                        summary = dto.getSenderNickname() + "님이 댓글에 좋아요를 눌렀어요.";
//                        break;
//                    case POST_LIKE:
//                        summary = dto.getSenderNickname() + "님이 포스트에 좋아요를 눌렀어요.";
//                        break;
//                    case RECEIVE_MESSAGE:
//                        summary = dto.getSenderNickname() + "님에게 메시지가 도착했어요.";
//                        break;
//                }
//                System.out.println("notificationType 실제 타입: " + (dto.getNotificationType() == null ? "null" : dto.getNotificationType().getClass().getName()));
//            }
//
//            dto.setMessageSummary(summary);
//
//            filteredNotifications.add(dto);
//        }
//
//        return filteredNotifications;
//    }

    @Override
    public List<NotificationResponseDTO> getNotificationsByReceiver(Long receiverId) {
        List<NotificationResponseDTO> notifications = notificationDAO.findAllByReceiverId(receiverId);

        if (notifications == null) {
            notifications = new ArrayList<>();
        }

        List<NotificationResponseDTO> filteredNotifications = new ArrayList<>();
        for (NotificationResponseDTO dto : notifications) {
            if (dto == null) {
                continue;
            }

            // 0. 프로필 기본값 세팅
            if (dto.getSenderFilePath() == null || dto.getSenderFileName() == null) {
                dto.setSenderFilePath("/images");
                dto.setSenderFileName("avatar_blank.png");
            }

            // 1. 읽음 상태 세팅
            if (dto.getNotificationChecked() == NotificationChecked.READ) {
                dto.setNotificationChecked(NotificationChecked.READ);
            } else {
                dto.setNotificationChecked(NotificationChecked.UNREAD);
            }

            // 2. 메세지 서머리 세팅
            String summary = "새 알림이 도착했어요.";

            NotificationType notificationType = null;
            if (dto.getNotificationType() != null) {
                try {
                    notificationType = NotificationType.valueOf(dto.getNotificationType().toString());
                } catch (IllegalArgumentException e) {

                }
            }

            if (notificationType != null) {
                switch (notificationType) {
                    case SUBSCRIBE:
                        summary = dto.getSenderNickname() + "님이 회원님의 채널을 구독했어요.";
                        dto.setSubSummary("채널 구독");
                        dto.setSubSubSummary(dto.getSenderNickname());
                        dto.setSubLink("/");
                        break;

                    case COMMUNITY_POST:
                        summary = dto.getSenderNickname() + "님의 채널에서 커뮤니티 글이 작성됐어요.";
                        dto.setSubSummary("새 커뮤니티 글");
                        dto.setSubSubSummary(dto.getSenderNickname());
                        dto.setSubLink("/");
                        break;

                    case POST_REPLY:
                        summary = dto.getSenderNickname() + "님이 댓글을 남겼어요.";
                        dto.setSubSummary("댓글 알림");
                        dto.setSubSubSummary(dto.getSenderNickname());
                        dto.setSubLink("/");
                        break;

                    case POST_REPLY_LIKE:
                        summary = dto.getSenderNickname() + "님이 댓글에 좋아요를 눌렀어요.";
                        dto.setSubSummary("댓글 좋아요");
                        dto.setSubSubSummary(dto.getSenderNickname());
                        dto.setSubLink("/");
                        break;

                    case POST_LIKE:
                        summary = dto.getSenderNickname() + "님이 포스트에 좋아요를 눌렀어요.";
                        dto.setSubSummary("포스트 좋아요");
                        dto.setSubSubSummary(dto.getSenderNickname());
                        dto.setSubLink("/");
                        break;

                    case RECEIVE_MESSAGE:
                        summary = dto.getSenderNickname() + "님에게 메시지가 도착했어요.";
                        dto.setSubSummary("쪽지 알림");
                        dto.setSubSubSummary(dto.getSenderNickname());
                        dto.setSubLink("/");
                        break;
                }
                System.out.println("notificationType 실제 타입: " + (dto.getNotificationType() == null ? "null" : dto.getNotificationType().getClass().getName()));
            }

            dto.setMessageSummary(summary);

            filteredNotifications.add(dto);
        }

        return filteredNotifications;
    }

    @Override
    public int getUnreadNotificationCount(Long receiverId) {
        return notificationDAO.countUnreadByReceiverId(receiverId);
    }

    @Override
    @Transactional
    public void markAllAsRead(Long receiverId) {
        notificationDAO.updateAllCheckedToRead(receiverId);
    }

}
