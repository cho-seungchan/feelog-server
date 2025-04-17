package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.ChannelPostScrapStatus;
import com.app.feelog.domain.vo.ChannelPostScrapVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelPostScrapDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long postId;
    private ChannelPostScrapStatus channelPostScrapStatus;
    private String createdDate;
    private String updatedDate;

    public ChannelPostScrapVO toVO() {
        return ChannelPostScrapVO.builder()
                .id(id)
                .memberId(memberId)
                .postId(postId)
                .channelPostScrapStatus(channelPostScrapStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }

}

