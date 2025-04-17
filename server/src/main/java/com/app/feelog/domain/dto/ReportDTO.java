package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.ReportStatus;
import com.app.feelog.domain.vo.ReportVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class ReportDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private ReportStatus reportStatus;
    private String createdDate;
    private String updatedDate;

    public ReportVO toVO() {
        return ReportVO.builder()
                .id(id)
                .reportStatus(reportStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
