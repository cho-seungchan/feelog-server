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

    @Builder
    public ChallengeDiaryVO(String createdDate, String updatedData, Long id, Long challengeId) {
        super(createdDate, updatedData);
        this.id = id;
        this.challengeId = challengeId;
    }
}