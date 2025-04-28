package com.app.feelog.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelPostReportListDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long reportMemberId;
    private Long postId;
    private String reportStatus;
    private String createdDate;
    private String updatedDate;
}
