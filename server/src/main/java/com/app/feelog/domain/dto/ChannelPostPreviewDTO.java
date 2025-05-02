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
public class ChannelPostPreviewDTO {
    @EqualsAndHashCode.Include
    private Long postId;
    private String postTitle;
    private String postContent;
    private Long channelId;

    private PostType postType;

    private String postFilePath;
    private String postFileName;

    private String tagList;
    private List<String> tagsList;

    private int viewCount;
    private int likeCount;

    private String memberProfilePath;
    private String memberProfileName;
    private String memberNickname;

    private String updatedDate;

}
