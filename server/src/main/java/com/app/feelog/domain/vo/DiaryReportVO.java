package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.DiaryReportStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class DiaryReportVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long diaryId;

    @Builder
    public DiaryReportVO(String createdDate, String updatedDate, Long id, Long memberId, Long diaryId) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberId = memberId;
        this.diaryId = diaryId;
    }
}