package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.CommonChallengeStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class CommonChallengeVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long taskId;

    @Builder
    public CommonChallengeVO(String createdDate, String updatedData, Long id, Long memberId, Long taskId) {
        super(createdDate, updatedData);
        this.id = id;
        this.memberId = memberId;
        this.taskId = taskId;
    }
}