package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.ChannelStatus;
import com.app.feelog.domain.vo.ChannelVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelDTO {
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
    private String createdDate;
    private String updatedDate;

    public ChannelVO toVO() {
        return ChannelVO.builder()
                .id(id)
                .channelTitle(channelTitle)
                .channelIntroduce(channelIntroduce)
                .channelUrl(channelUrl)
                .channelFilePath(channelFilePath)
                .channelFileName(channelFileName)
                .channelFileSize(channelFileSize)
                .memberId(memberId)
                .channelStatus(channelStatus)
                .createdDate(createdDate)
                .updatedData(updatedDate)
                .build();
    }
}