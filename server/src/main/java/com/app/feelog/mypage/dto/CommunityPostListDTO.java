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
public class CommunityPostListDTO {
    @EqualsAndHashCode.Include
    private Long        id;
    private String      postContent;
    private Long        memberId;
    private String      memberNickname;
    private String      memberFilePath;
    private String      memberFileName;
    private List<CommunityPostFileVO> files;
    private Long        channelId;
    private String      channelUrl;
    private int         likeCount;
    private int         replyCount;
    private boolean     iLike;
    private String      createdDate;
    private String      updatedDate;
    private String      timeAgo;
}
