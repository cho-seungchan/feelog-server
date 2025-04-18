package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.*;
import com.app.feelog.domain.vo.MemberVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class MemberDTO {
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
<<<<<<< HEAD
    private MemberNotificationPostReply memberNotificationPostReply;
    private MemberNotificationPostReplyLike memberNotificationPostReplyLike;
    private MemberNotificationPostLike       memberNotificationPostLike;
    private MemberNotificationSubscribe memberNotificationSubscribe;
    private MemberNotificationCommunityPost  memberNotificationCommunityPost;
    private MemberNotificationMessage        memberNotificationMessage;
=======
    private String memberNotificationPostReply;
    private String memberNotificationPostReplyLike;
    private String memberNotificationPostLike;
    private String memberNotificationSubscribe;
    private String memberNotificationCommunityPost;
    private String memberNotificationMessage;
>>>>>>> c90771130bcf5aa5d70c6135dc39c10be7dbec83
    private MemberStatus memberStatus;
    private String createdDate;
    private String updatedDate;

    public MemberVO toVO() {
        return MemberVO.builder()
                .id(id)
                .memberEmail(memberEmail)
                .memberPassword(memberPassword)
                .memberNickname(memberNickname)
                .memberIntroduce(memberIntroduce)
                .memberFilePath(memberFilePath)
                .memberFileName(memberFileName)
                .memberFileSize(memberFileSize)
                .memberType(memberType)
                .memberNotificationPostReply(memberNotificationPostReply)
                .memberNotificationPostReplyLike(memberNotificationPostReplyLike)
                .memberNotificationPostLike(memberNotificationPostLike)
                .memberNotificationSubscribe(memberNotificationSubscribe)
                .memberNotificationCommunityPost(memberNotificationCommunityPost)
                .memberNotificationMessage(memberNotificationMessage)
                .memberStatus(memberStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}