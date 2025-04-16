package com.app.feelog.domain.vo;

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
    private String challengetaskType;
    private String challengeStatus;

    @Builder
    public ChallengeVO(String createdDate, String updatedData, Long id, String challengeComplete, String challengetaskType, String challengeStatus) {
        super(createdDate, updatedData);
        this.id = id;
        this.challengeComplete = challengeComplete;
        this.challengetaskType = challengetaskType;
        this.challengeStatus = challengeStatus;
    }
}