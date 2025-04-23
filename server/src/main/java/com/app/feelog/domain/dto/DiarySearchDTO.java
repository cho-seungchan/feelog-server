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
public class DiarySearchDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String title;
    private String nickname;
    private String thumbnailUrl;
    private String createdDate;
    private String tags;
    private List<String> tagsList;
}
