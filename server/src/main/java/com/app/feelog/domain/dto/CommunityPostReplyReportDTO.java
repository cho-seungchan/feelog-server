package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.CommunityPostReplyReportVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @ToString @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CommunityPostReplyReportDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long replyId;

    public CommunityPostReplyReportVO toVO() {
        return CommunityPostReplyReportVO.builder()
                .id(id)
                .memberId(memberId)
                .replyId(replyId)
                .build();
    }
}
