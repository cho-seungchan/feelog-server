package com.app.feelog.mypage.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class NotifyReplyListDTO {
    @EqualsAndHashCode.Include
    private Long        id;
    private String      replyContent;
    private String      memberNickname;
    private String      memberFilePath;
    private String      memberFileName;
    private Long        memberId;        // 작성자 아이디
    private Long        postId;
    private Long        memberChannelId; // 작성자 채널 아이디
    private String      timeAgo;         // 현재 시점과 생성일간 시간차 계산한 결과
    private String      createdDate;
    private String      updatedDate;

}