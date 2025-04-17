package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.ReceiveMessageNotificationVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReceiveMessageNotificationDTO {
    @EqualsAndHashCode.Include
    Long id;
    Long receiveMessageId;

    public ReceiveMessageNotificationVO toVO() {
        return ReceiveMessageNotificationVO.builder()
                .id(id)
                .receiveMessageId(receiveMessageId)
                .build();
    }
}
