package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.DiaryNameOpen;
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
    private String diaryTitle;
    private String diaryContent;
    private DiaryOpen diaryOpen;
    private DiaryNameOpen diaryNameOpen;
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
                .diaryTitle(diaryTitle)
                .diaryContent(diaryContent)
                .diaryOpen(diaryOpen)
                .diaryNameOpen(diaryNameOpen)
                .diaryFilePath(diaryFilePath)
                .diaryFileName(diaryFileName)
                .diaryFileSize(diaryFileSize)
                .diaryWeather(diaryWeather)
                .memberId(memberId)
                .feelId(feelId)
                .diaryStatus(diaryStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}