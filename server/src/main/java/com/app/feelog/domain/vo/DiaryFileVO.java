package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class DiaryFileVO{
    @EqualsAndHashCode.Include
    private Long id;
    private Long diaryId;
    private Long fileId;

    @Builder
    public DiaryFileVO(Long id, Long diaryId, Long fileId) {
        this.id = id;
        this.diaryId = diaryId;
        this.fileId = fileId;
    }
}
