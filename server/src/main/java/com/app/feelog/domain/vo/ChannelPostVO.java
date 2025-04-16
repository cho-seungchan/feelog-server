package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class ChannelPostVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String postType;
    private String postFilePath;
    private String postFileName;
    private String postFileSize;
    private Long memberId;
    private Long channelId;
    private String channelPostStatus;

    @Builder
    public ChannelPostVO(String createdDate, String updatedData, Long id, String postType, String postFilePath, String postFileName, String postFileSize, Long memberId, Long channelId, String channelPostStatus) {
        super(createdDate, updatedData);
        this.id = id;
        this.postType = postType;
        this.postFilePath = postFilePath;
        this.postFileName = postFileName;
        this.postFileSize = postFileSize;
        this.memberId = memberId;
        this.channelId = channelId;
        this.channelPostStatus = channelPostStatus;
    }
}