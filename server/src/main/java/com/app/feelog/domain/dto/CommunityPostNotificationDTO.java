package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.CommunityPostNotificationVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CommunityPostNotificationDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long communityPostId;

    public CommunityPostNotificationVO toVO() {
        return CommunityPostNotificationVO.builder()
                .id(id)
                .communityPostId(communityPostId)
                .build();
    }
}
