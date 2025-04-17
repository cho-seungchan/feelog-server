package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.PostTagVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PostTagDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long tagId;
    private String createdDate;
    private String updatedDate;

    public PostTagVO toVO() {
        return PostTagVO.builder()
                .id(id)
                .tagId(tagId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
