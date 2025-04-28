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
public class ChannelPostLikeVO extends SuperPeriod {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long postId;

}