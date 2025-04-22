package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.TagStatus;
import com.app.feelog.domain.vo.ChannelPostTagVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelPostTagDTO {
    private Long id; // tag ID
    private String contents;
    private TagStatus tagStatus;
    private Long channelPostId;
    private String createdDate;
    private String updatedDate;

    public ChannelPostTagVO toVO() {
        return ChannelPostTagVO.builder()
                .id(id)
                .contents(contents)
                .tagStatus(tagStatus)
                .channelPostId(channelPostId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}