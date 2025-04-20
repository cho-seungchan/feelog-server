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
public class CommonChallengeVO extends ChallengeVO{
    @EqualsAndHashCode.Include
    private Long memberId;
    private Long taskId;

    @Builder
    public CommonChallengeVO(String createdDate, String updatedDate, Long id, ChallengeComplete challengeComplete, ChallengeStatus challengeStatus, Long memberId, Long taskId) {
        super(createdDate,  updatedDate, id, challengeComplete, challengeStatus);
        this.memberId = memberId;
        this.taskId = taskId;
    }
}