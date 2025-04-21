package com.app.feelog.mypage.dto;

import com.app.feelog.domain.enumeration.ChallengeComplete;
import com.app.feelog.domain.enumeration.ChallengeStatus;
import com.app.feelog.domain.vo.CommonChallengeVO;
import com.app.feelog.domain.vo.CommonTaskVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CommonTaskChallengeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String commonTaskName;
    private String commonTaskImg;
    private String commonTaskTell;
    private String commonTaskUrl;
    private String commonTaskAddr;
    private Double commonTaskLot;
    private Double commonTaskLat;
    private String commonTaskServiceName;
    private String commonTaskReqPage;
    private String commonTaskStatus;
    private Long memberId;
    private Long taskId;
    private ChallengeComplete challengeComplete;
    private ChallengeStatus challengeStatus;
    private String createdDate;
    private String updatedDate;

    public CommonTaskVO createCommonTaskVO() {
        return CommonTaskVO.builder()
                .id(id)
                .commonTaskName(commonTaskName)
                .commonTaskImg(commonTaskImg)
                .commonTaskTell(commonTaskTell)
                .commonTaskUrl(commonTaskUrl)
                .commonTaskAddr(commonTaskAddr)
                .commonTaskLot(commonTaskLot)
                .commonTaskLat(commonTaskLat)
                .commonTaskServiceName(commonTaskServiceName)
                .commonTaskReqPage(commonTaskReqPage)
                .commonTaskStatus(commonTaskStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }

    public CommonChallengeVO createCommonChallengeVO() {
        return CommonChallengeVO.builder()
                .id(id)
                .memberId(memberId)
                .taskId(taskId)
                .challengeComplete(challengeComplete)
                .challengeStatus(challengeStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}