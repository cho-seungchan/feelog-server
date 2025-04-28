package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.PostLikeNotificationVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PostLikeNotificationDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long postLikeId;

    public PostLikeNotificationVO toVO() {
        return PostLikeNotificationVO.builder()
                .id(id)
                .postLikeId(postLikeId)
                .build();
    }
}
