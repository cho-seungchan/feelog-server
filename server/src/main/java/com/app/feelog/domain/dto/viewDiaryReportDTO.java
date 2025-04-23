package com.app.feelog.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class viewDiaryReportDTO {
    @EqualsAndHashCode.Include
    Long id;
    String reportStatus;
    String updatedDate;
    Long reportMemberId;
    String reportMemberNickname;
    Long reportDiaryId;
    String reportDiaryTitle;
    String reportDiaryContent;
    Long reportDiaryMemberId;
    String reportDiaryMemberNickname;
}
