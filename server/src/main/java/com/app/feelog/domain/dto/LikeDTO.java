package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.LikeStatus;
import com.app.feelog.domain.vo.LikeVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class LikeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private LikeStatus likeStatus;
    private String createdDate;
    private String updatedDate;

    public LikeVO toVO() {
        return LikeVO.builder()
                .id(id)
                .likeStatus(likeStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
