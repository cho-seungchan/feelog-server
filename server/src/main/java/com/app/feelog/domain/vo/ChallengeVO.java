package com.app.feelog.domain.vo;

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
    private String challengeComplete;
    private ChallengeStatus challengeStatus;

    @Builder
    public ChallengeVO(String createdDate, String updatedData, Long id, String challengeComplete, ChallengeStatus challengeStatus) {
        super(createdDate, updatedData);
        this.id = id;
        this.challengeComplete = challengeComplete;
        this.challengeStatus = challengeStatus;
    }
}