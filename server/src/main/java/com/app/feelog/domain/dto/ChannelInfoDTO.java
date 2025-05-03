package com.app.feelog.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelInfoDTO {
    @EqualsAndHashCode.Include
    private Long channelId;
    private String channelTitle;
    private String channelIntroduce;
    private String channelUrl;
    private String channelFilePath;
    private String channelFileName;
    private Long memberId;
    private String memberNickname;
    private int subscriberCount;
    private int noticeCount;

}
