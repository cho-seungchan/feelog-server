package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.DiaryOpen;
import com.app.feelog.domain.enumeration.DiaryStatus;
import com.app.feelog.domain.vo.DiaryVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DiaryDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String diaryContent;
    private DiaryOpen diaryOpen;
    private String diaryFilePath;
    private String diaryFileName;
    private String diaryFileSize;
    private String diaryWeather;
    private Long   memberId;
    private Long   feelId;
    private DiaryStatus diaryStatus;
    private String createdDate;
    private String updatedDate;

    public DiaryVO toVO() {
        return DiaryVO.builder()
                .id(id)
                .diaryContent(diaryContent)
                .diaryOpen(diaryOpen)
                .diaryFilePath(diaryFilePath)
                .diaryFileName(diaryFileName)
                .diaryFileSize(diaryFileSize)
                .diaryWeather(diaryWeather)
                .memberId(memberId)
                .feelId(feelId)
                .diaryStatus(diaryStatus)
                .createdDate(createdDate)
                .updatedData(updatedDate)
                .build();
    }
}