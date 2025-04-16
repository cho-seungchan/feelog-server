package com.app.feelog.domain.dto;

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
    private String diaryReplyReportStatus;
    private String createdDate;
    private String updatedDate;

    public DiaryReplyReportVO toVO() {
        return DiaryReplyReportVO.builder()
                .id(id)
                .memberId(memberId)
                .replyId(replyId)
                .diaryReplyReportStatus(diaryReplyReportStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}