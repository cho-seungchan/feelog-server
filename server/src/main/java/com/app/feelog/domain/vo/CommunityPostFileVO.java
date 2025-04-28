package com.app.feelog.domain.vo;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class CommunityPostFileVO extends FileVO {
    @EqualsAndHashCode.Include
    private Long postId;

//    @Builder
//    public CommunityPostFileVO(String createdDate, String updatedDate, Long id, Long communityPostId) {
//        super(createdDate, updatedDate);
//        this.id = id;
//        this.postId = postId;
//    }
}