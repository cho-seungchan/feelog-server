package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class ChallengeDiaryVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long challengeId;

    @Builder
    public ChallengeDiaryVO(Long id, Long challengeId) {
        this.id = id;
        this.challengeId = challengeId;
    }
}