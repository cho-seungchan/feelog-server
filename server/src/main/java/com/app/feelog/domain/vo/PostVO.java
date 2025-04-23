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
public class PostVO extends SuperPeriod {
    @EqualsAndHashCode.Include
    private Long id;
    private String postTitle;
    private String postContent;
    private PostStatus postStatus;

//    public PostVO(String createdDate, String updatedDate, Long id, String postTitle, String postContent, PostStatus postStatus) {
//        super(createdDate, updatedDate);
//        this.id = id;
//        this.postTitle = postTitle;
//        this.postContent = postContent;
//        this.postStatus = postStatus;
//    }
}