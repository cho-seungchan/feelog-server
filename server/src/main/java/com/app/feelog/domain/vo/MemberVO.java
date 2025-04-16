package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class MemberVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberNickname;
    private String memberIntroduce;
    private String memberFilePath;
    private String memberFileName;
    private String memberFileSize;
    private String memberType;
    private String memberSuspend;
    private String memberNotificationPostReply;
    private String memberNotificationPostReplyLike;
    private String memberNotificationPostLike;
    private String memberNotificationSubscribe;
    private String memberNotificationCommunityPost;
    private String memberNotificationMessage;
    private String memberStatus;

    @Builder
    public MemberVO(String createdDate, String updatedData, Long id, String memberEmail, String memberPassword, String memberNickname, String memberIntroduce, String memberFilePath, String memberFileName, String memberFileSize, String memberType, String memberSuspend, String memberNotificationPostReply, String memberNotificationPostReplyLike, String memberNotificationPostLike, String memberNotificationSubscribe, String memberNotificationCommunityPost, String memberNotificationMessage, String memberStatus) {
        super(createdDate, updatedData);
        this.id = id;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberNickname = memberNickname;
        this.memberIntroduce = memberIntroduce;
        this.memberFilePath = memberFilePath;
        this.memberFileName = memberFileName;
        this.memberFileSize = memberFileSize;
        this.memberType = memberType;
        this.memberSuspend = memberSuspend;
        this.memberNotificationPostReply = memberNotificationPostReply;
        this.memberNotificationPostReplyLike = memberNotificationPostReplyLike;
        this.memberNotificationPostLike = memberNotificationPostLike;
        this.memberNotificationSubscribe = memberNotificationSubscribe;
        this.memberNotificationCommunityPost = memberNotificationCommunityPost;
        this.memberNotificationMessage = memberNotificationMessage;
        this.memberStatus = memberStatus;
    }
}