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
    protected Long id;
    protected ChallengeComplete challengeComplete;
    protected ChallengeStatus challengeStatus;

    public ChallengeVO(String createdDate, String updatedDate, Long id, ChallengeComplete challengeComplete, ChallengeStatus challengeStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.challengeComplete = challengeComplete;
        this.challengeStatus = challengeStatus;
    }
}