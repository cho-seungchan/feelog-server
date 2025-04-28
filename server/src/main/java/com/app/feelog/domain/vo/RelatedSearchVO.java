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
@NoArgsConstructor
@SuperBuilder
public class RelatedSearchVO extends SuperPeriod {
    @EqualsAndHashCode.Include
    private Long id;
    private String keyword;
    private Long tagId;
    private Long memberId;
}
