package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.PostStatus;
import com.app.feelog.domain.vo.PostJKVO;
import com.app.feelog.domain.vo.PostVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PostJkDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String postTitle;
    private String postContent;
    private PostStatus postStatus;
    private String createdDate;
    private String updatedDate;

    public PostJKVO toVO() {
        return PostJKVO.builder()
                .id(id)
                .postTitle(postTitle)
                .postContent(postContent)
                .postStatus(postStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}