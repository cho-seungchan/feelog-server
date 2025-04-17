package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.SubscribeNotificationVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SubscribeNotificationDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long subscribeId;

    public SubscribeNotificationVO toVO() {
        return SubscribeNotificationVO.builder()
                .id(id)
                .subscribeId(subscribeId)
                .build();
    }
}
