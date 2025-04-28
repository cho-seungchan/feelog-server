package com.app.feelog.domain.vo;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@SuperBuilder
public class PostReplyNotificationVO extends NotificationVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long postReplyId;
}
