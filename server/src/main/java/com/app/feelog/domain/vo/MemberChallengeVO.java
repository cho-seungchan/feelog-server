package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class MemberChallengeVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long taskId;
    private String memberChallengeStatus;

    @Builder
    public MemberChallengeVO(String createdDate, String updatedData, Long id, Long memberId, Long taskId, String memberChallengeStatus) {
        super(createdDate, updatedData);
        this.id = id;
        this.memberId = memberId;
        this.taskId = taskId;
        this.memberChallengeStatus = memberChallengeStatus;
    }
}