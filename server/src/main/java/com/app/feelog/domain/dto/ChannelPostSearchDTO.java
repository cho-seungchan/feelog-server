package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.PostType;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelPostSearchDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String title;
    private String thumbnailUrl;
    private String nickname;
    private String createdDate;
    private PostType postType;
    private String tags;
    private List<String> tagsList;
}
