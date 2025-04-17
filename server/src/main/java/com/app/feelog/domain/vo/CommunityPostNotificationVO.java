package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CommunityPostNotificationVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long communityPostId;

    @Builder
    public CommunityPostNotificationVO(Long id, Long communityPostId) {
        this.id = id;
        this.communityPostId = communityPostId;
    }
}
