package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class CommonTaskVO extends Period {
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

    @Builder
    public CommonTaskVO(String createdDate, String updatedDate, Long id, String commonTaskName, String commonTaskImg, String commonTaskTell, String commonTaskUrl, String commonTaskAddr, Double commonTaskLot, Double commonTaskLat, String commonTaskServiceName, String commonTaskReqPage, String commonTaskStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.commonTaskName = commonTaskName;
        this.commonTaskImg = commonTaskImg;
        this.commonTaskTell = commonTaskTell;
        this.commonTaskUrl = commonTaskUrl;
        this.commonTaskAddr = commonTaskAddr;
        this.commonTaskLot = commonTaskLot;
        this.commonTaskLat = commonTaskLat;
        this.commonTaskServiceName = commonTaskServiceName;
        this.commonTaskReqPage = commonTaskReqPage;
        this.commonTaskStatus = commonTaskStatus;
    }
}