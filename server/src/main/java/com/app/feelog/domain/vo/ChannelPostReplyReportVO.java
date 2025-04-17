package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class ChannelPostReplyReportVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long replyId;

    @Builder
    public ChannelPostReplyReportVO(String createdDate, String updatedDate, Long id, Long memberId, Long replyId) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberId = memberId;
        this.replyId = replyId;
    }
}