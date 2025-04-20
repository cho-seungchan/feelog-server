package com.app.feelog.mypage.dto;

import com.app.feelog.domain.enumeration.ChallengeComplete;
import com.app.feelog.domain.enumeration.ChallengeStatus;
import com.app.feelog.domain.enumeration.MemberTaskPoolStatus;
import com.app.feelog.domain.vo.MemberChallengeVO;
import com.app.feelog.domain.vo.MemberTaskPoolVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class MemberTaskPoolChallengeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long taskId;
    private String memberTaskPoolContent;
    private String memberTaskPoolFilePath;
    private String memberTaskPoolFileName;
    private MemberTaskPoolStatus memberTaskPoolStatus;
    private ChallengeComplete challengeComplete;
    private ChallengeStatus challengeStatus;
    private String createdDate;
    private String updatedDate;

    public MemberChallengeVO createMemberChallengeVO() {
        return MemberChallengeVO.builder()
                .id(id)
                .memberId(memberId)
                .taskId(taskId)
                .challengeComplete(challengeComplete)
                .challengeStatus(challengeStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }

    public MemberTaskPoolVO createMemberTaskPoolVO() {
        return MemberTaskPoolVO.builder()
                .id(taskId)
                .memberTaskPoolContent(memberTaskPoolContent)
                .memberTaskPoolFilePath(memberTaskPoolFilePath)
                .memberTaskPoolFileName(memberTaskPoolFileName)
                .memberTaskPoolStatus(memberTaskPoolStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}