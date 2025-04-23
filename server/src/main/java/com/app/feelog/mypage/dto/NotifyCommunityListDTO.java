package com.app.feelog.mypage.dto;

import com.app.feelog.domain.dto.CommunityPostFileDTO;
import com.app.feelog.domain.enumeration.ChallengeComplete;
import com.app.feelog.domain.enumeration.ChallengeStatus;
import com.app.feelog.domain.enumeration.PostStatus;
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
public class NotifyCommunityListDTO {
    @EqualsAndHashCode.Include
    private Long        id;
    private String      postTitle;
    private String      postContent;
    private String      memberNickname;
    private String      memberFilePath;
    private String      memberFileName;
    private Long        memberId;        // 작성자 아이디
    private Long        channelId;
    private Long        memberChannelId; // 작성자 채널 아이디
    private String      timeAgo;         // 현재 시점과 생성일간 시간차 계산한 결과
    private String      createdDate;
    private String      updatedDate;

}