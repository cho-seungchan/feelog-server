package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class ChallengeDiaryVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long challengeId;
    private String challengeDiaryStatus;

    @Builder
    public ChallengeDiaryVO(String createdDate, String updatedData, Long id, Long challengeId, String challengeDiaryStatus) {
        super(createdDate, updatedData);
        this.id = id;
        this.challengeId = challengeId;
        this.challengeDiaryStatus = challengeDiaryStatus;
    }
}