package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.FileStatus;
import com.app.feelog.domain.vo.ChannelPostFileVO;
import com.app.feelog.domain.vo.DiaryFileVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelPostFileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String filePath;
    private String fileName;
    private String fileSize;
    private FileStatus fileStatus;
    private String createdDate;
    private String updatedDate;
    private Long postId;

    public ChannelPostFileVO toVO() {
        return ChannelPostFileVO.builder()
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .id(id)
                .filePath(filePath)
                .fileName(fileName)
                .fileSize(fileSize)
                .fileStatus(fileStatus)
                .postId(postId)
                .build();
    }
}