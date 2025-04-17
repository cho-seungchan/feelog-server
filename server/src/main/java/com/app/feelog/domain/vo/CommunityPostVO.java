package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class CommunityPostVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long channelId;

    @Builder
    public CommunityPostVO(String createdDate, String updatedDate, Long id, Long memberId, Long channelId) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberId = memberId;
        this.channelId = channelId;
    }
}