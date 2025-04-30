package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.DiaryScoreVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DiaryScoreDTO {

    @EqualsAndHashCode.Include
    private Long id;
    private String scoreLevelName;
    private String scoreMessage;
    private String scoreFilePath;
    private String scoreFileName;
    private String createdDate;
    private String updatedDate;

    public DiaryScoreVO toVO() {
        return DiaryScoreVO.builder()
                .id(id)
                .scoreLevelName(scoreLevelName)
                .scoreMessage(scoreMessage)
                .scoreFilePath(scoreFilePath)
                .scoreFileName(scoreFileName)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();

    }
}