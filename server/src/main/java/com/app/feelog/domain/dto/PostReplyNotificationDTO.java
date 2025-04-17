package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.PostReplyNotificationVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PostReplyNotificationDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long postReplyId;

    public PostReplyNotificationVO toVO() {
        return PostReplyNotificationVO.builder()
                .id(id)
                .postReplyId(postReplyId)
                .build();
    }
}
