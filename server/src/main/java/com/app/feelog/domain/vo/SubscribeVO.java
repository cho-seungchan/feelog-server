package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.SubscribeStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class SubscribeVO extends Period{
    @EqualsAndHashCode.Include
    private Long id;
    private Long channelId;
    private Long memberId;
    private SubscribeStatus subscribeStatus;

    @Builder
    public SubscribeVO(String createdDate, String updatedData, Long id, Long channelId, Long memberId, SubscribeStatus subscribeStatus) {
        super(createdDate, updatedData);
        this.id = id;
        this.channelId = channelId;
        this.memberId = memberId;
        this.subscribeStatus = subscribeStatus;
    }
}
