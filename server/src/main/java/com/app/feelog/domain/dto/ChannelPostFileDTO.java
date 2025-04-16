package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.ChannelPostFileVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelPostFileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long postId;
    private String channelPostFileStatus;
    private String createdDate;
    private String updatedDate;

    public ChannelPostFileVO toVO() {
        return ChannelPostFileVO.builder()
                .id(id)
                .postId(postId)
                .channelPostFileStatus(channelPostFileStatus)
                .createdDate(createdDate)
                .updatedData(updatedDate)
                .build();
    }
}