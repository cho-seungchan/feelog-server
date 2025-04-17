package com.app.feelog.domain.dto;

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

    public CommonChallengeVO toVO() {
        return CommonChallengeVO.builder()
                .id(id)
                .memberId(memberId)
                .taskId(taskId)
                .build();
    }
}