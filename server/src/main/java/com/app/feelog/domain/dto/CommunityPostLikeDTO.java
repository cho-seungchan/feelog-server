package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.CommunityPostLikeVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CommunityPostLikeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long communityId;
    private String createdDate;
    private String updatedDate;

    public CommunityPostLikeVO toVO() {
        return CommunityPostLikeVO.builder()
                .id(id)
                .memberId(memberId)
                .communityId(communityId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
