package com.app.feelog.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@SuperBuilder
@NoArgsConstructor
public class DiaryScoreVO extends SuperPeriod{

    @EqualsAndHashCode.Include
    private Long id;
    private String scoreLevelName;
    private String scoreMessage;
    private String scoreFilePath;
    private String scoreFileName;

}
