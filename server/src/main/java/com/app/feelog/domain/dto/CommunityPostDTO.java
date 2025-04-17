package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.CommunityPostVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CommunityPostDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long channelId;
    private String createdDate;
    private String updatedDate;

    public CommunityPostVO toVO() {
        return CommunityPostVO.builder()
                .id(id)
                .memberId(memberId)
                .channelId(channelId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
