package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.FileStatus;
import com.app.feelog.domain.vo.FileVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class FileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String filePath;
    private String fileName;
    private String fileSize;
    private FileStatus fileStatus;
    private String createdDate;
    private String updatedDate;

    public FileVO toVO() {
        return FileVO.builder()
                .id(id)
                .filePath(filePath)
                .fileName(fileName)
                .fileSize(fileSize)
                .fileStatus(fileStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}