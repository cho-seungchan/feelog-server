package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.MemberChallengeStatus;
import com.app.feelog.domain.vo.MemberChallengeVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class MemberChallengeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long taskId;
    private String createdDate;
    private String updatedDate;

    public MemberChallengeVO toVO() {
        return MemberChallengeVO.builder()
                .id(id)
                .memberId(memberId)
                .taskId(taskId)
                .createdDate(createdDate)
                .updatedData(updatedDate)
                .build();
    }
}
