package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PostLikeNotificationVO {
    @EqualsAndHashCode.Include
    Long id;
    Long postLikeId;

    @Builder
    public PostLikeNotificationVO(Long id, Long postLikeId) {
        this.id = id;
        this.postLikeId = postLikeId;
    }
}
