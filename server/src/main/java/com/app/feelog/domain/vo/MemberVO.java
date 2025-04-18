package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.*;
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
    private MemberType memberType;
    private MemberNotificationPostReply     memberNotificationPostReply;
    private MemberNotificationPostReplyLike memberNotificationPostReplyLike;
    private MemberNotificationPostLike      memberNotificationPostLike;
    private MemberNotificationSubscribe     memberNotificationSubscribe;
    private MemberNotificationCommunityPost memberNotificationCommunityPost;
    private MemberNotificationMessage       memberNotificationMessage;
    private MemberStatus memberStatus;

    @Builder
    public MemberVO(String createdDate, String updatedDate, Long id, String memberEmail, String memberPassword, String memberNickname, String memberIntroduce, String memberFilePath, String memberFileName, String memberFileSize, MemberType memberType,
                    MemberNotificationPostReply memberNotificationPostReply, MemberNotificationPostReplyLike memberNotificationPostReplyLike,
                    MemberNotificationPostLike memberNotificationPostLike, MemberNotificationSubscribe memberNotificationSubscribe,
                    MemberNotificationCommunityPost memberNotificationCommunityPost, MemberNotificationMessage memberNotificationMessage,
                    MemberStatus memberStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberNickname = memberNickname;
        this.memberIntroduce = memberIntroduce;
        this.memberFilePath = memberFilePath;
        this.memberFileName = memberFileName;
        this.memberFileSize = memberFileSize;
        this.memberType = memberType;
        this.memberNotificationPostReply = memberNotificationPostReply;
        this.memberNotificationPostReplyLike = memberNotificationPostReplyLike;
        this.memberNotificationPostLike = memberNotificationPostLike;
        this.memberNotificationSubscribe = memberNotificationSubscribe;
        this.memberNotificationCommunityPost = memberNotificationCommunityPost;
        this.memberNotificationMessage = memberNotificationMessage;
        this.memberStatus = memberStatus;
    }
}