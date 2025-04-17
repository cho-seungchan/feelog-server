package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PostReplyLikeNotificationVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long postReplyLikeId;

    @Builder
    public PostReplyLikeNotificationVO(Long id, Long postReplyLikeId) {
        this.id = id;
        this.postReplyLikeId = postReplyLikeId;
    }
}
