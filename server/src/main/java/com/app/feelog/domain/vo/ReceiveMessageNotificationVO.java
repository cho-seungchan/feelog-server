package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ReceiveMessageNotificationVO {
    @EqualsAndHashCode.Include
    Long id;
    Long receiveMessageId;

    @Builder
    public ReceiveMessageNotificationVO(Long id, Long receiveMessageId) {
        this.id = id;
        this.receiveMessageId = receiveMessageId;
    }
}
