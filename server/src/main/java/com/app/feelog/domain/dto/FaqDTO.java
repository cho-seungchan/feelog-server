package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.FaqStatus;
import com.app.feelog.domain.vo.FaqVO;
import com.app.feelog.domain.vo.Period;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FaqDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String faqTitle;
    private String faqContent;
    private Long memberId;
    private FaqStatus faqStatus;
    private String createdDate;
    private String updatedDate;

    public FaqVO toVO() {
        return FaqVO.builder()
                .id(id)
                .faqTitle(faqTitle)
                .faqContent(faqContent)
                .memberId(memberId)
                .faqStatus(faqStatus)
                .createdDate(createdDate)
                .updatedData(updatedDate)
                .build();
    }

}
