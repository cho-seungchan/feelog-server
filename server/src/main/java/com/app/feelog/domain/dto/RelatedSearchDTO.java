package com.app.feelog.domain.dto;


import com.app.feelog.domain.vo.RelatedSearchVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class RelatedSearchDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String keyword;
    private Long tagId;
    private Long memberId;
    private String createdDate;
    private String updatedDate;

    public RelatedSearchVO toVO() {
        return RelatedSearchVO.builder()
                .id(id)
                .keyword(keyword)
                .tagId(tagId)
                .memberId(memberId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }

}
