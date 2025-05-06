package com.app.feelog.domain.dto.joinDTO;

import com.app.feelog.domain.enumeration.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DiaryJoinDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private String memberNickname;
    private String memberFilePath;
    private String memberFileName;
    private MemberStatus memberStatus;
    private String diaryTitle;
    private String diaryContent;
    private DiaryOpen diaryOpen;
    private DiaryNameOpen diaryNameOpen;
    private String diaryFilePath;
    private String diaryFileName;
    private DiaryStatus diaryStatus;
    private String updatedDate;
    private Long channelId;
    private String channelUrl;
    private String channelTitle;
    private String channelFilePath;
    private String channelFileName;
    private ChannelStatus channelStatus;
    private List<String> diaryTags;
    private int diaryReadCount;
    private int replyCount;
    private int likeCount;
    private boolean isSubscribedPost;
    private boolean isLiked;
    private Long diaryScore;
    private String scoreMessage;
}
