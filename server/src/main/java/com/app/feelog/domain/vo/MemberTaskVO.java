package com.app.feelog.domain.vo;

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
    private String memberTaskStatus;

    @Builder
    public MemberTaskVO(String createdDate, String updatedData, Long id, Long taskPoolId, String memberTaskStatus) {
        super(createdDate, updatedData);
        this.id = id;
        this.taskPoolId = taskPoolId;
        this.memberTaskStatus = memberTaskStatus;
    }
}