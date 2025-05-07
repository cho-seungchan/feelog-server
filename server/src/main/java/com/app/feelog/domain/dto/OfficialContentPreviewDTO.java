package com.app.feelog.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OfficialContentPreviewDTO {

    @EqualsAndHashCode.Include
    private Long id;

    private String title;

    private String filePath;
    private String fileName;
    private String taskUrl;
    private String noticeContent;

    private String createdDate;

    private String channelUrl;
    private String channelFilePath;
    private String channelFileName;
    private String channelTitle;
    private String channelIntroduce;

    private String contentType;

    private Long memberId;
    private String memberNickname;

}
