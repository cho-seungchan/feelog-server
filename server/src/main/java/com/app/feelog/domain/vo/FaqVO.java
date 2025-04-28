package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.FaqStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@SuperBuilder
public class FaqVO extends SuperPeriod{
    @EqualsAndHashCode.Include
    private Long id;
    private String faqTitle;
    private String faqContent;
    private Long memberId;
    private FaqStatus faqStatus;

}
