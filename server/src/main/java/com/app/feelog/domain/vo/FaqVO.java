package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.FaqStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class FaqVO extends Period{
    @EqualsAndHashCode.Include
    private Long id;
    private String faqTitle;
    private String faqContent;
    private Long memberId;
    private FaqStatus faqStatus;

    @Builder
    public FaqVO(String createdDate, String updatedDate, Long id, String faqTitle, String faqContent, Long memberId, FaqStatus faqStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.faqTitle = faqTitle;
        this.faqContent = faqContent;
        this.memberId = memberId;
        this.faqStatus = faqStatus;
    }
}
