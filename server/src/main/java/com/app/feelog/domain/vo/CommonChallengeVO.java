package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class CommonChallengeVO{
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long taskId;

    @Builder
    public CommonChallengeVO(Long id, Long memberId, Long taskId) {
        this.id = id;
        this.memberId = memberId;
        this.taskId = taskId;
    }
}