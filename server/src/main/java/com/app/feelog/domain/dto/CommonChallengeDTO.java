package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.ChallengeComplete;
import com.app.feelog.domain.enumeration.ChallengeStatus;
import com.app.feelog.domain.vo.ChallengeVO;
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
    private ChallengeComplete challengeComplete;
    private ChallengeStatus challengeStatus;
    private String createdDate;
    private String updatedDate;

    public CommonChallengeVO toVO() {
        return CommonChallengeVO.builder()
                .id(id)
                .memberId(memberId)
                .taskId(taskId)
                .challengeComplete(challengeComplete)
                .challengeStatus(challengeStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }

}