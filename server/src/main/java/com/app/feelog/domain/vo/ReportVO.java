package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.ReportStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class ReportVO extends SuperPeriod {
    @EqualsAndHashCode.Include
    private Long id;
    private ReportStatus reportStatus;

}
