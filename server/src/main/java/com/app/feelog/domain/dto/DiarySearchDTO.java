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
public class DiarySearchDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String title;
    private String content;
    private String nickname;
    private DiaryOpen diaryOpen;
    private DiaryNameOpen diaryNameOpen;
    private String thumbnailUrl;
    private String memberProfileImg;
    private Long memberId;
    private String createdDate;
    private String tags;
    private List<String> tagsList;
}
