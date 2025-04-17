package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class DiaryReplyVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long diaryId;

    @Builder
    public DiaryReplyVO(String createdDate, String updatedDate, Long id, Long memberId, Long diaryId) {
        this.id = id;
        this.memberId = memberId;
        this.diaryId = diaryId;
    }
}