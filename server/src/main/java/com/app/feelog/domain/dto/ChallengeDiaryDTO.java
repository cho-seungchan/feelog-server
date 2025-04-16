package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.ChallengeDiaryVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChallengeDiaryDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long challengeId;
    private String challengeDiaryStatus;
    private String createdDate;
    private String updatedDate;

    public ChallengeDiaryVO toVO() {
        return ChallengeDiaryVO.builder()
                .id(id)
                .challengeId(challengeId)
                .challengeDiaryStatus(challengeDiaryStatus)
                .createdDate(createdDate)
                .updatedData(updatedDate)
                .build();
    }
}


