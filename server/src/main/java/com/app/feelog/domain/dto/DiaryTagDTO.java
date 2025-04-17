package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.DiaryTagVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DiaryTagDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long tagId;
    private String createdDate;
    private String updatedDate;

    public DiaryTagVO toVO() {
        return DiaryTagVO.builder()
                .id(id)
                .tagId(tagId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }

}
