package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.PostStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class PostVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String postTitle;
    private String postSubTitle;
    private String postContent;
    private PostStatus postStatus;

    @Builder
    public PostVO(String createdDate, String updatedData, Long id, String postTitle, String postSubTitle, String postContent, PostStatus postStatus) {
        super(createdDate, updatedData);
        this.id = id;
        this.postTitle = postTitle;
        this.postSubTitle = postSubTitle;
        this.postContent = postContent;
        this.postStatus = postStatus;
    }
}