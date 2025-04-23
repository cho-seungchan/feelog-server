package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.PostStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class CommunityPostVO extends PostVO {
    @EqualsAndHashCode.Include
    private Long memberId;
    private Long channelId;

//    @Builder
//    public CommunityPostVO(String createdDate, String updatedDate, Long id, String postTitle, String postContent, PostStatus postStatus,
//            Long memberId, Long channelId) {
//        super(createdDate, updatedDate, id, postTitle, postContent, postStatus);
//        this.memberId = memberId;
//        this.channelId = channelId;
//    }
}