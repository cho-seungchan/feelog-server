package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.TagStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class DiaryTagVO extends TagVO {
    private Long diaryId;

//    @Builder
//    public DiaryTagVO(String createdDate, String updatedDate,
//                      Long id, String contents, TagStatus tagStatus,
//                      Long diaryId) {
//        super(createdDate, updatedDate, id, contents, tagStatus);
//        this.diaryId = diaryId;
//    }
}
