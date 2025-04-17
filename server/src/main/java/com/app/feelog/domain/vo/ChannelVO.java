package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.ChannelStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class ChannelVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String channelTitle;
    private String channelIntroduce;
    private String channelUrl;
    private String channelFilePath;
    private String channelFileName;
    private String channelFileSize;
    private Long   memberId;
    private ChannelStatus channelStatus;

    @Builder
    public ChannelVO(String createdDate, String updatedDate, Long id, String channelTitle, String channelIntroduce, String channelUrl, String channelFilePath, String channelFileName, String channelFileSize, Long memberId, ChannelStatus channelStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.channelTitle = channelTitle;
        this.channelIntroduce = channelIntroduce;
        this.channelUrl = channelUrl;
        this.channelFilePath = channelFilePath;
        this.channelFileName = channelFileName;
        this.channelFileSize = channelFileSize;
        this.memberId = memberId;
        this.channelStatus = channelStatus;
    }
}