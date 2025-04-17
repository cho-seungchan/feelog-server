package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.PostReplyLikeNotificationVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PostReplyLikeNotificationDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long postReplyLikeId;

    public PostReplyLikeNotificationVO toVO() {
        return PostReplyLikeNotificationVO.builder()
                .id(id)
                .postReplyLikeId(postReplyLikeId)
                .build();
    }
}
