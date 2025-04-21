package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.TagStatus;
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
    private Long tagId;
    private String contents;
    private TagStatus tagStatus;
    private String createdDate;
    private String updatedDate;
    private Long diaryId;


    public DiaryTagVO toVO() {
        return DiaryTagVO.builder()
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .id(tagId)
                .contents(contents)
                .tagStatus(tagStatus)
                .diaryId(diaryId)
                .build();
    }
}
