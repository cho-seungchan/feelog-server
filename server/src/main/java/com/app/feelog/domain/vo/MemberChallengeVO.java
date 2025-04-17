package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.MemberChallengeStatus;
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
    private MemberChallengeStatus memberChallengeStatus;

    @Builder
    public MemberChallengeVO(String createdDate, String updatedData, Long id, Long memberId, Long taskId, MemberChallengeStatus memberChallengeStatus) {
        super(createdDate, updatedData);
        this.id = id;
        this.memberId = memberId;
        this.taskId = taskId;
        this.memberChallengeStatus = memberChallengeStatus;
    }
}