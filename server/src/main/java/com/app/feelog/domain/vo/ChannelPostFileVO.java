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

    @Builder
    public ChannelPostFileVO(String createdDate, String updatedData, Long id, Long postId) {
        super(createdDate, updatedData);
        this.id = id;
        this.postId = postId;
    }
}