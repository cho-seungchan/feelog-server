package com.app.feelog.mypage.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class NotifySubscribeListDTO {
    @EqualsAndHashCode.Include
    private Long        id;
    private String      channelTitle;
    private String      channelIntroduce;
    private String      channelUrl;
    private String      channelFilePath;
    private String      channelFileName;
    private Long        memberId;
    private String      memberNickname;
    private String      memberFilePath;
    private String      memberFileName;
    private String      createdDate;
    private String      updatedDate;

}