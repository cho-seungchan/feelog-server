package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.DiaryReplyStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class DiaryReplyVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long diaryId;
    private DiaryReplyStatus diaryReplyStatus;

    @Builder
    public DiaryReplyVO(String createdDate, String updatedDate, Long id, Long memberId, Long diaryId, DiaryReplyStatus diaryReplyStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberId = memberId;
        this.diaryId = diaryId;
        this.diaryReplyStatus = diaryReplyStatus;
    }
}