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
    private String memberTaskPoolContent;
    private String memberTaskPoolFilePath;
    private String memberTaskPoolFileName;
    private MemberTaskPoolStatus memberTaskPoolStatus;

    @Builder
    public MemberTaskPoolVO(String createdDate, String updatedDate, Long id, String memberTaskPoolContent, String memberTaskPoolFilePath, String memberTaskPoolFileName, MemberTaskPoolStatus memberTaskPoolStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberTaskPoolContent = memberTaskPoolContent;
        this.memberTaskPoolFilePath = memberTaskPoolFilePath;
        this.memberTaskPoolFileName = memberTaskPoolFileName;
        this.memberTaskPoolStatus = memberTaskPoolStatus;
    }
}