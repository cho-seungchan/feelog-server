package com.app.feelog.mypage.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class StorageLikeListDTO {
    @EqualsAndHashCode.Include
    private Long        id;
    private Long        memberId;
    private String      memberNickname;
    private String      memberFilePath;
    private String      memberFileName;
    private Long        postId;
    private String      postTitle;
    private String      postContent;
    private String      postFilePath;
    private String      postFileName;
    private int         postReadCount;
    private int         postReplyCount;
    private int         postLikeCount;
    private Long        channelId;
    private String      createdDate;
    private String      updatedDate;
    private String      timeAgo;         // 현재 시점과 생성일간 시간차 계산한 결과

}