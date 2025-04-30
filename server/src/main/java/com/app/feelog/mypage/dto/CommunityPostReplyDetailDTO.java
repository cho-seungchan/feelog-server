package com.app.feelog.mypage.dto;

import com.app.feelog.domain.vo.CommunityPostFileVO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CommunityPostReplyDetailDTO {
    @EqualsAndHashCode.Include
    private Long        id;
    private String      replyContent;
    private String      replyFilePath;
    private String      replyFileName;
    private Long        memberId;
    private Long        postId;
    private String      createdDate;
    private String      updatedDate;
    private String      timeAgo;
    private String      memberNickname;
    private String      memberFilePath;
    private String      memberFileName;
    private Long        channelId;
    private String      channelUrl;
    private int         likeCount;
    private int         reportCount;
    //    로그인 한 회원의 액션
    private boolean     iLike;
    private boolean     iReport;
}
