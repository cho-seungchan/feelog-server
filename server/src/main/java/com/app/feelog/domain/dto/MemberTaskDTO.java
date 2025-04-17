package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.MemberTaskStatus;
import com.app.feelog.domain.vo.MemberTaskVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class MemberTaskDTO {
    @EqualsAndHashCode.Include
    private Long   id;
    private Long   taskPoolId;
    private MemberTaskStatus memberTaskStatus;
    private String createdDate;
    private String updatedDate;

    public MemberTaskVO toVO() {
        return MemberTaskVO.builder()
                .id(id)
                .taskPoolId(taskPoolId)
                .memberTaskStatus(memberTaskStatus)
                .createdDate(createdDate)
                .updatedData(updatedDate)
                .build();
    }
}