package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.ChannelPostScrapStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class ChannelPostScrapVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long postId;
    private ChannelPostScrapStatus channelPostScrapStatus;

    @Builder
    public ChannelPostScrapVO(String createdDate, String updatedDate, Long id, Long memberId, Long postId, ChannelPostScrapStatus channelPostScrapStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberId = memberId;
        this.postId = postId;
        this.channelPostScrapStatus = channelPostScrapStatus;
    }
}