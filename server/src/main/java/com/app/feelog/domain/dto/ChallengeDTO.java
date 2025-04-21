package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.ChallengeComplete;
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
    protected Long id;
    protected ChallengeComplete challengeComplete;
    protected ChallengeStatus challengeStatus;
    protected String createdDate;
    protected String updatedDate;

}
