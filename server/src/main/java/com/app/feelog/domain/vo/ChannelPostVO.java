package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.PostType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class ChannelPostVO{
    @EqualsAndHashCode.Include
    private Long   id;
    private PostType postType;
    private String postFilePath;
    private String postFileName;
    private String postFileSize;
    private Long   memberId;
    private Long   channelId;

//    @Builder
//    public ChannelPostVO(Long id, PostType postType, String postFilePath, String postFileName, String postFileSize, Long memberId, Long channelId) {
//        this.id = id;
//        this.postType = postType;
//        this.postFilePath = postFilePath;
//        this.postFileName = postFileName;
//        this.postFileSize = postFileSize;
//        this.memberId = memberId;
//        this.channelId = channelId;
//    }
}