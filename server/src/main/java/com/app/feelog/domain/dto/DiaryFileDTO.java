package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.DiaryFileVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DiaryFileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long diaryId;
    private Long fileId;

    public DiaryFileVO toVO() {
        return DiaryFileVO.builder()
                .id(id)
                .diaryId(diaryId)
                .fileId(fileId)
                .build();
    }
}