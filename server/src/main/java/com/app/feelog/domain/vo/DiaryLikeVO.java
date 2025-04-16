package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class DiaryLikeVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long diaryId;
    private String diaryLikeStatus;

    @Builder
    public DiaryLikeVO(String createdDate, String updatedDate, Long id, Long memberId, Long diaryId, String diaryLikeStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberId = memberId;
        this.diaryId = diaryId;
        this.diaryLikeStatus = diaryLikeStatus;
    }
}