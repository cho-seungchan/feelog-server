package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.ChannelPostReportVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelPostReportDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long postId;
    private String createdDate;
    private String updatedDate;

    public ChannelPostReportVO toVO() {
        return ChannelPostReportVO.builder()
                .id(id)
                .memberId(memberId)
                .postId(postId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
