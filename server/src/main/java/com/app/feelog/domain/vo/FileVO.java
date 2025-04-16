package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class FileVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String filePath;
    private String fileName;
    private String fileSize;
    private String fileStatus;

    @Builder
    public FileVO(String createdDate, String updatedData, Long id, String filePath, String fileName, String fileSize, String fileStatus) {
        super(createdDate, updatedData);
        this.id = id;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileStatus = fileStatus;
    }
}