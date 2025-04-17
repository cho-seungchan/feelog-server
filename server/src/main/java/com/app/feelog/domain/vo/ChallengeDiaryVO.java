package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.ChallengeDiaryStatus;
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
    private ChallengeDiaryStatus challengeDiaryStatus;

    @Builder
    public ChallengeDiaryVO(String createdDate, String updatedDate, Long id, Long challengeId, ChallengeDiaryStatus challengeDiaryStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.challengeId = challengeId;
        this.challengeDiaryStatus = challengeDiaryStatus;
    }
}