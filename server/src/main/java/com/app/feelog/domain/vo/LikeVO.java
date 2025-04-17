package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.LikeStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class LikeVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private LikeStatus likeStatus;

    @Builder
    public LikeVO(String createdDate, String updatedDate, Long id, LikeStatus likeStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.likeStatus = likeStatus;
    }
}
