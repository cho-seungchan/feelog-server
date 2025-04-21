package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.FileStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class FileVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String filePath;
    private String fileName;
    private String fileSize;
    private FileStatus fileStatus;

    @Builder
    public FileVO(String createdDate, String updatedDate, Long id, String filePath, String fileName, String fileSize, FileStatus fileStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileStatus = fileStatus;
    }
}