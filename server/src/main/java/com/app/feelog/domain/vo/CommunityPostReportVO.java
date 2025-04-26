package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class CommunityPostReportVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long postId;

    @Builder
    public CommunityPostReportVO(String createdDate, String updatedDate, Long id, Long memberId, Long postId) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberId = memberId;
        this.postId = postId;
    }
}
