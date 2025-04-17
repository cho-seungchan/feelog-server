package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.DiaryReplyVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DiaryReplyDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long diaryId;

    public DiaryReplyVO toVO() {
        return DiaryReplyVO.builder()
                .id(id)
                .memberId(memberId)
                .diaryId(diaryId)
                .build();
    }
}