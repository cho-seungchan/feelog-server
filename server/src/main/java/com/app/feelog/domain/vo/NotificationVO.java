package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.NotificationChecked;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@SuperBuilder
public class NotificationVO extends  SuperPeriod {
    @EqualsAndHashCode.Include
    private Long id;
    private Long senderId;
    private Long receiverId;
    private NotificationChecked notificationChecked;

}
