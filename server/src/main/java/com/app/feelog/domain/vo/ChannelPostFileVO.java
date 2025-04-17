package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.ChannelPostFileStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class ChannelPostFileVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long postId;
    private ChannelPostFileStatus channelPostFileStatus;

    @Builder
    public ChannelPostFileVO(String createdDate, String updatedDate, Long id, Long postId, ChannelPostFileStatus channelPostFileStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.postId = postId;
        this.channelPostFileStatus = channelPostFileStatus;
    }
}