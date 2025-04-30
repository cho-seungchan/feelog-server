package com.app.feelog.domain.dto;

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
    private String diaryTitle;
    private String diaryContent;
    private String diaryFilePath;
    private String diaryFileName;
    private String updatedDate;

    // 채널 정보
    private String channelTitle;
    private String channelFilePath;
    private String channelFileName;

    // 부가 정보
    private Integer viewCount;
    private Integer likeCount;

    // 태그
    private List<String> tags;

}
