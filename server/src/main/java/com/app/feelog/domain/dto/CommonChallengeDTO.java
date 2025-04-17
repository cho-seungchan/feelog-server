package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.CommonChallengeStatus;
import com.app.feelog.domain.vo.CommonChallengeVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CommonChallengeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long taskId;
    private CommonChallengeStatus commonChallengeStatus;
    private String createdDate;
    private String updatedDate;

    public CommonChallengeVO toVO() {
        return CommonChallengeVO.builder()
                .id(id)
                .memberId(memberId)
                .taskId(taskId)
                .commonChallengeStatus(commonChallengeStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}