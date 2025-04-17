package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.NotificationChecked;
import com.app.feelog.domain.vo.NotificationVO;
import com.app.feelog.domain.vo.Period;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NotificationDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long senderId;
    private Long receiverId;
    private NotificationChecked notificationChecked;
    private String createdDate;
    private String updatedDate;

    public NotificationVO toVO() {
        return NotificationVO.builder()
                .id(id)
                .senderId(senderId)
                .receiverId(receiverId)
                .notificationChecked(notificationChecked)
                .createdDate(createdDate)
                .updatedData(updatedDate)
                .build();
    }
}
