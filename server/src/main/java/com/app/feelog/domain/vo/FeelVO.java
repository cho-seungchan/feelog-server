package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class FeelVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Integer feelCode;
    private String feelFilePath;
    private String feelFileName;
    private String feelFileSize;

    @Builder
    public FeelVO(String createdDate, String updatedDate, Long id, Integer feelCode, String feelFilePath, String feelFileName, String feelFileSize) {
        super(createdDate, updatedDate);
        this.id = id;
        this.feelCode = feelCode;
        this.feelFilePath = feelFilePath;
        this.feelFileName = feelFileName;
        this.feelFileSize = feelFileSize;
    }
}