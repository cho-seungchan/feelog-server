package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.NotificationChecked;
import com.app.feelog.domain.enumeration.NotificationType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotificationResponseDTO {
    private Long notificationId;     // 알림 ID
    private Long senderId;            // 보낸 사람 ID
    private String senderNickname;    // 보낸 사람 닉네임
    private String senderFilePath;
    private String senderFileName;
    private NotificationType notificationType;  // 알림 종류
    private String messageSummary;    // 보여줄 메세지 문구
    private String createdDate;       // 생성일자
    private NotificationChecked notificationChecked;           // 읽음 여부
    private String thumbnailPath;
    private String thumbnailName;
}