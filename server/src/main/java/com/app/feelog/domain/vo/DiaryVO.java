package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class DiaryVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String diaryContent;
    private String diaryOpen;
    private String diaryFilePath;
    private String diaryFileName;
    private String diaryFileSize;
    private String diaryWeather;
    private Long memberId;
    private Long feelId;
    private String diaryStatus;

    @Builder
    public DiaryVO(String createdDate, String updatedData, Long id, String diaryContent, String diaryOpen, String diaryFilePath, String diaryFileName, String diaryFileSize, String diaryWeather, Long memberId, Long feelId, String diaryStatus) {
        super(createdDate, updatedData);
        this.id = id;
        this.diaryContent = diaryContent;
        this.diaryOpen = diaryOpen;
        this.diaryFilePath = diaryFilePath;
        this.diaryFileName = diaryFileName;
        this.diaryFileSize = diaryFileSize;
        this.diaryWeather = diaryWeather;
        this.memberId = memberId;
        this.feelId = feelId;
        this.diaryStatus = diaryStatus;
    }
}