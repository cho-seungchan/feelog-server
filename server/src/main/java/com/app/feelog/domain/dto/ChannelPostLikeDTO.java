package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.ChannelPostLikeVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelPostLikeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long postId;
    private String createdDate;
    private String updatedDate;
    private Long postMemberId;

    public ChannelPostLikeVO toVO() {
        return ChannelPostLikeVO.builder()
                .id(id)
                .memberId(memberId)
                .postId(postId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
