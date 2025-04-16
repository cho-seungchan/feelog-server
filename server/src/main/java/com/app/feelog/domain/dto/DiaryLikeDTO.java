package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.DiaryLikeVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DiaryLikeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long diaryId;
    private String diaryLikeStatus;
    private String createdDate;
    private String updatedDate;

    public DiaryLikeVO toVO() {
        return DiaryLikeVO.builder()
                .id(id)
                .memberId(memberId)
                .diaryId(diaryId)
                .diaryLikeStatus(diaryLikeStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}