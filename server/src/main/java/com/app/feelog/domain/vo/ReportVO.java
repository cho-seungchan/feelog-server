package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.ReportStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class ReportVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private ReportStatus reportStatus;

    @Builder
    public ReportVO(String createdDate, String updatedDate, Long id, ReportStatus reportStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.reportStatus = reportStatus;
    }
}
