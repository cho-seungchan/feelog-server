package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.TagStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class TagVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String contents;
    private TagStatus tagStatus;

    @Builder
    public TagVO(String createdDate, String updatedDate, Long id, String contents, TagStatus tagStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.contents = contents;
        this.tagStatus = tagStatus;
    }
}
