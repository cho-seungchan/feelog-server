package com.app.feelog.mypage.dto;

import com.app.feelog.domain.enumeration.ChallengeComplete;
import com.app.feelog.domain.enumeration.ChallengeStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class AllChallengeListDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String type; // 개별, 공통
    private String commonTaskName;
    private String commonTaskImg;
    private String commonTaskTell;
    private String commonTaskUrl;
    private String commonTaskAddr;
    private Double commonTaskLot;
    private Double commonTaskLat;
    private String commonTaskStatus;
    private String memberTaskPoolContent;
    private String memberTaskPoolFilePath;
    private String memberTaskPoolFileName;
    private Long memberId;
    private Long taskId;
    private ChallengeComplete challengeComplete;
    private ChallengeStatus challengeStatus;
    private String createdDate;
    private String updatedDate;

}