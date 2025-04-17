package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.MemberTaskStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class MemberTaskVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long taskPoolId;
    private MemberTaskStatus memberTaskStatus;

    @Builder
    public MemberTaskVO(String createdDate, String updatedDate, Long id, Long taskPoolId, MemberTaskStatus memberTaskStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.taskPoolId = taskPoolId;
        this.memberTaskStatus = memberTaskStatus;
    }
}