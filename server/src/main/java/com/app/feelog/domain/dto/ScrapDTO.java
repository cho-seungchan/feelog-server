package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.ScrapVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ScrapDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long postId;
    private String scranStatus;
    private String createdDate;
    private String updatedDate;

    public ScrapVO toVO() {
        return ScrapVO.builder()
                .id(id)
                .memberId(memberId)
                .postId(postId)
                .scrapStatus(scranStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
