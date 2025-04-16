package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class DiaryReplyReportVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long replyId;
    private String diaryReplyReportStatus;

    @Builder
    public DiaryReplyReportVO(String createdDate, String updatedDate, Long id, Long memberId, Long replyId, String diaryReplyReportStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberId = memberId;
        this.replyId = replyId;
        this.diaryReplyReportStatus = diaryReplyReportStatus;
    }
}