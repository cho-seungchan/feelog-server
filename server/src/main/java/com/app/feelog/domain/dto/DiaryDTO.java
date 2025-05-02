package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.DiaryNameOpen;
import com.app.feelog.domain.enumeration.DiaryOpen;
import com.app.feelog.domain.enumeration.DiaryStatus;
import com.app.feelog.domain.vo.DiaryVO;
import com.app.feelog.domain.vo.FileVO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private Long    diaryScore;
    private Long   memberId;
    private DiaryStatus diaryStatus;
    private String createdDate;
    private String updatedDate;
    private int diaryReadCount;

    // 첨부파일 및 태그
    private List<Long> fileIds;      // summernote용
    private List<String> tags;       // 새 태그 문자열 리스트
    private List<FileVO> fileList;
    private Long challengeId;

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
                .diaryScore(diaryScore)
                .memberId(memberId)
                .diaryStatus(diaryStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .diaryReadCount(diaryReadCount)
                .build();
    }
}