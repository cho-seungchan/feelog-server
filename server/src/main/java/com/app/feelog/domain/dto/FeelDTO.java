package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.FileStatus;
import com.app.feelog.domain.vo.FeelVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class FeelDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Integer feelCode;
    private String feelFilePath;
    private String feelFileName;
    private String feelFileSize;
    private String createdDate;
    private String updatedDate;

    public FeelVO toVO() {
        return FeelVO.builder()
                .id(id)
                .feelCode(feelCode)
                .feelFilePath(feelFilePath)
                .feelFileName(feelFileName)
                .feelFileSize(feelFileSize)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}