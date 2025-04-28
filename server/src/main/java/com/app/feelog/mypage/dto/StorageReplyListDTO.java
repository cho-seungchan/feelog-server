package com.app.feelog.mypage.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class StorageReplyListDTO {
    @EqualsAndHashCode.Include
    private Long        id;
    private String      replyContent;
    private Long        postId;
    private String      postTitle;
    private String      postContent;
    private String      postFilePath;
    private String      postFileName;
    private String      createdDate;
    private String      updatedDate;
    private String      timeAgo;         // 현재 시점과 생성일간 시간차 계산한 결과

}