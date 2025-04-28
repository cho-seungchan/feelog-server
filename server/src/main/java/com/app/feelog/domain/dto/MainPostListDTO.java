package com.app.feelog.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class MainPostListDTO {
    @EqualsAndHashCode.Include
        private Long id;
        private String postType;
        private String postMainFileName;
        private String postMainFilePath;
        private int postReadCount;;
        private int postReplyCount;
        private int postLikeCount;
        private Long channelId;
        private String postTitle;
        private String postContent;
        private String postStatus;
        private String updatedDate;
        private Long channelMemberId;
        private String channelTitle;
        private String channelFilePath;
        private String channelFileName;
        private String channelUrl;
        private String channelMemberNickname;
        private String memberFileName;
        private String memberFilePath;
        private List<ChannelPostTagListDTO> tagList;
        private boolean isScrapped;
}
