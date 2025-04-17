package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class DiaryTagVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long tagId;

    @Builder
    public DiaryTagVO(String createdDate, String updatedDate, Long id, Long tagId) {
        super(createdDate, updatedDate);
        this.id = id;
        this.tagId = tagId;
    }
}
