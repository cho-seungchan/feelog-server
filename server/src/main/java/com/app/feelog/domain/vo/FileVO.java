package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.FileStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class FileVO extends SuperPeriod {
    @EqualsAndHashCode.Include
    private Long id;
    private String filePath;
    private String fileName;
    private String fileSize;
    private FileStatus fileStatus;

}