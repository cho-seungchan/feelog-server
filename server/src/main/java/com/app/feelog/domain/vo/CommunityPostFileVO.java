package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class CommunityPostFileVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long communityId;

    @Builder
    public CommunityPostFileVO(String createdDate, String updatedDate, Long id, Long communityId) {
        super(createdDate, updatedDate);
        this.id = id;
        this.communityId = communityId;
    }
}