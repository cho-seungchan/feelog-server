package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.PostStatus;
import com.app.feelog.domain.vo.PostVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PostDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String postTitle;
    private String postSubTitle;
    private String postContent;
    private PostStatus postStatus;
    private String createdDate;
    private String updatedDate;

    public PostVO toVO() {
        return PostVO.builder()
                .id(id)
                .postTitle(postTitle)
                .postSubTitle(postSubTitle)
                .postContent(postContent)
                .postStatus(postStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}