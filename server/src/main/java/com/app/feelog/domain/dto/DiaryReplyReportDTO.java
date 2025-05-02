package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.ReportStatus;
import com.app.feelog.domain.vo.DiaryReplyReportVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DiaryReplyReportDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long replyId;
    private ReportStatus reportStatus;
    private String updatedDate;
    private String createdDate;

    public DiaryReplyReportVO toVO() {
        return DiaryReplyReportVO.builder()
                .id(id)
                .memberId(memberId)
                .replyId(replyId)
                .reportStatus(reportStatus)
                .updatedDate(updatedDate)
                .createdDate(createdDate)
                .build();
    }
}