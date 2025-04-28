package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.CommunityPostReportVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CommunityPostReportDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long postId;
    private String createdDate;
    private String updatedDate;

    public CommunityPostReportVO toVO() {
        return CommunityPostReportVO.builder()
                .id(id)
                .memberId(memberId)
                .postId(postId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
