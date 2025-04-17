package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.ChallengeStatus;
import com.app.feelog.domain.vo.ChallengeVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChallengeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String challengeComplete;
    private ChallengeStatus challengeStatus;
    private String createdDate;
    private String updatedDate;

    public ChallengeVO toVO() {
        return ChallengeVO.builder()
                .id(id)
                .challengeComplete(challengeComplete)
                .challengeStatus(challengeStatus)
                .createdDate(createdDate)
                .updatedData(updatedDate)
                .build();
    }
}
