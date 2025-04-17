package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.CommonTaskVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CommonTaskDTO {
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
    private String createdDate;
    private String updatedDate;

    public CommonTaskVO toVO() {
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
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}