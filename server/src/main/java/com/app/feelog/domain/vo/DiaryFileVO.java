package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.FileStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class DiaryFileVO extends FileVO {
    private Long diaryId;

//    @Builder
//    public DiaryFileVO(String createdDate, String updatedDate,
//                       Long id, String filePath, String fileName,
//                       String fileSize, FileStatus fileStatus,
//                       Long diaryId) {
//        super(createdDate, updatedDate, id, filePath, fileName, fileSize, fileStatus);
//        this.diaryId = diaryId;
//    }
}
