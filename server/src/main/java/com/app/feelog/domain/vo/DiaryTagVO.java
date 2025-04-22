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
}
