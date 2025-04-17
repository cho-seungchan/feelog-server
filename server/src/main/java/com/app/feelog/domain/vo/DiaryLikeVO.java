package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.DiaryLikeStatus;
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

    @Builder
    public DiaryLikeVO(String createdDate, String updatedDate, Long id, Long memberId, Long diaryId) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberId = memberId;
        this.diaryId = diaryId;
    }
}