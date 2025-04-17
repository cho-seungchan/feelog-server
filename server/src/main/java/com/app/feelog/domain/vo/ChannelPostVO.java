package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.ChannelPostStatus;
import com.app.feelog.domain.enumeration.PostType;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class ChannelPostVO extends Period {
    @EqualsAndHashCode.Include
    private Long   id;
    private PostType postType;
    private String postFilePath;
    private String postFileName;
    private String postFileSize;
    private Long   memberId;
    private Long   channelId;

    @Builder
    public ChannelPostVO(String createdDate, String updatedData, Long id, PostType postType, String postFilePath, String postFileName, String postFileSize, Long memberId, Long channelId) {
        super(createdDate, updatedData);
        this.id = id;
        this.postType = postType;
        this.postFilePath = postFilePath;
        this.postFileName = postFileName;
        this.postFileSize = postFileSize;
        this.memberId = memberId;
        this.channelId = channelId;
    }
}