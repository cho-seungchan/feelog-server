package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.FileStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class DiaryFileVO extends FileVO {
    private Long id;
    private Long diaryId;
}
