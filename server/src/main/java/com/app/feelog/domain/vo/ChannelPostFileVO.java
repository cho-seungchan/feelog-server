package com.app.feelog.domain.vo;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class ChannelPostFileVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long postId;

//    @Builder
//    public ChannelPostFileVO(Long id, Long postId) {
//        this.id = id;
//        this.postId = postId;
//    }
}