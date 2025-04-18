package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.NotificationChecked;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class NotificationVO extends  Period{
    @EqualsAndHashCode.Include
    private Long id;
    private Long senderId;
    private Long receiverId;
    private NotificationChecked notificationChecked;

    @Builder
    public NotificationVO(String createdDate, String updatedDate, Long id, Long senderId, Long receiverId, NotificationChecked notificationChecked) {
        super(createdDate, updatedDate);
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.notificationChecked = notificationChecked;
    }
}
