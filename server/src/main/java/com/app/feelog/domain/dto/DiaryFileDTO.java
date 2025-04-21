package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.FileStatus;
import com.app.feelog.domain.vo.DiaryFileVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DiaryFileDTO {
    private Long id;
    private String filePath;
    private String fileName;
    private String fileSize;
    private FileStatus fileStatus;
    private String createdDate;
    private String updatedDate;
    private Long diaryId;

    public DiaryFileVO toVO() {
        return DiaryFileVO.builder()
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .id(id)
                .filePath(filePath)
                .fileName(fileName)
                .fileSize(fileSize)
                .fileStatus(fileStatus)
                .diaryId(diaryId)
                .build();
    }
}