package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.ChallengeComplete;
import com.app.feelog.domain.enumeration.ChallengeStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class ChallengeVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private ChallengeComplete challengeComplete;
    private ChallengeStatus challengeStatus;

    @Builder
    public ChallengeVO(String createdDate, String updatedDate, Long id, ChallengeComplete challengeComplete, ChallengeStatus challengeStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.challengeComplete = challengeComplete;
        this.challengeStatus = challengeStatus;
    }
}