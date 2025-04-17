package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.MemberTaskPoolStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class MemberTaskPoolVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String mamberTaskPoolContent;
    private String mamberTaskPoolFilePath;
    private String mamberTaskPoolFileName;
    private String mamberTaskPoolFileSize;
    private MemberTaskPoolStatus memberTaskPoolStatus;

    @Builder
    public MemberTaskPoolVO(String createdDate, String updatedDate, Long id, String mamberTaskPoolContent, String mamberTaskPoolFilePath, String mamberTaskPoolFileName, String mamberTaskPoolFileSize, MemberTaskPoolStatus memberTaskPoolStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.mamberTaskPoolContent = mamberTaskPoolContent;
        this.mamberTaskPoolFilePath = mamberTaskPoolFilePath;
        this.mamberTaskPoolFileName = mamberTaskPoolFileName;
        this.mamberTaskPoolFileSize = mamberTaskPoolFileSize;
        this.memberTaskPoolStatus = memberTaskPoolStatus;
    }
}