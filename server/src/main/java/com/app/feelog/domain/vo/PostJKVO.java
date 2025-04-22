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
public class PostJKVO extends SuperPeriod {
    @EqualsAndHashCode.Include
    private Long id;
    private String postTitle;
    private String postSubTitle;
    private String postContent;
    private PostStatus postStatus;
}