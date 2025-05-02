package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.DiaryNameOpen;
import com.app.feelog.domain.enumeration.DiaryOpen;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DiaryPreviewDTO {
    @EqualsAndHashCode.Include
    private Long id;

    // 일기 기본 정보
    private String diaryTitle;
    private String diaryContent;
    private String diaryFilePath;
    private String diaryFileName;
    private String updatedDate;
    private String memberNickname;

    // 채널 정보
    private String channelTitle;
    private String channelUrl;
    private String channelFilePath;
    private String channelFileName;

    // 부가 정보
    private Integer viewCount;
    private Integer likeCount;

    private DiaryOpen diaryOpen;
    private DiaryNameOpen diaryNameOpen;


    // 태그 목록 (조회 시 GROUP_CONCAT → split)
    private List<String> tags;

}
