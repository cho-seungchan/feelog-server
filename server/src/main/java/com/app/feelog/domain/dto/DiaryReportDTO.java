package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.ReportStatus;
import com.app.feelog.domain.vo.DiaryReportVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DiaryReportDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long diaryId;
    private String createdDate;
    private String updatedDate;
    private ReportStatus reportStatus;

    public DiaryReportVO toVO() {
        return DiaryReportVO.builder()
                .id(id)
                .memberId(memberId)
                .diaryId(diaryId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .reportStatus(reportStatus)
                .build();
    }
}