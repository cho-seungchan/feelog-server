package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.TagStatus;
import com.app.feelog.domain.vo.TagVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class TagDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String contents;
    private TagStatus tagStatus;
    private String createdDate;
    private String updatedDate;

    public TagVO toVO() {
        return TagVO.builder()
                .id(id)
                .contents(contents)
                .tagStatus(tagStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
