package com.app.feelog.domain.dto.joinDTO;

import com.app.feelog.domain.enumeration.ChannelStatus;
import com.app.feelog.domain.enumeration.DiaryNameOpen;
import com.app.feelog.domain.enumeration.DiaryOpen;
import com.app.feelog.domain.enumeration.DiaryStatus;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DiaryDetailDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String diaryTitle;
    private String diaryContent;
    private DiaryOpen diaryOpen;
    private DiaryNameOpen diaryNameOpen;
    private Long memberId;
    private DiaryStatus diaryStatus;
    private String updatedDate;
    private int diaryReadCount;
    private Long diaryScore;
    private String memberNickname;
    private String memberFileName;
    private String memberFilePath;
    private Long channelId;
    private String channelTitle;
    private String channelFileName;
    private String channelFilePath;
    private ChannelStatus channelStatus;
    private String channelUrl;
    private List<String> tags;
    private boolean isLiked;
    private int diaryLikeCount;
    private int diaryReplyCount;
    private String scoreMessage;
}
