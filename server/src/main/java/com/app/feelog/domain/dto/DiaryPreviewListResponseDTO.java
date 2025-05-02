package com.app.feelog.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DiaryPreviewListResponseDTO {

    private List<DiaryPreviewDTO> diaries;
    private int totalCount;

}
