package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PostReplyNotificationVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long postReplyId;

    @Builder
    public PostReplyNotificationVO(Long id, Long postReplyId) {
        this.id = id;
        this.postReplyId = postReplyId;
    }
}
