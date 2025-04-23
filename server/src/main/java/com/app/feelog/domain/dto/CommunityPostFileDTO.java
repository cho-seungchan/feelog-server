package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.CommunityPostFileVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CommunityPostFileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long communityPostId;
    private String createdDate;
    private String updatedDate;

    public CommunityPostFileVO toVO() {
        return CommunityPostFileVO.builder()
                .id(id)
                .communityPostId(communityPostId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }

}

