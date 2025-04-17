package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class SubscribeNotificationVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long subscribeId;

    @Builder
    public SubscribeNotificationVO(Long id, Long subscribeId) {
        this.id = id;
        this.subscribeId = subscribeId;
    }
}
