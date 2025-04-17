package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.ChannelPostReplyReportVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelPostReplyReportDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long replyId;
    private String createdDate;
    private String updatedDate;

    public ChannelPostReplyReportVO toVO() {
        return ChannelPostReplyReportVO.builder()
                .id(id)
                .memberId(memberId)
                .replyId(replyId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
