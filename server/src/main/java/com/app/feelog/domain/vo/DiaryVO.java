package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.DiaryNameOpen;
import com.app.feelog.domain.enumeration.DiaryOpen;
import com.app.feelog.domain.enumeration.DiaryStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class DiaryVO extends SuperPeriod {
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
    private Long memberId;
    private Long feelId;
    private DiaryStatus diaryStatus;
}