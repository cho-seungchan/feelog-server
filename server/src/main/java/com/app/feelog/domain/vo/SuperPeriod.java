package com.app.feelog.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter @ToString
@NoArgsConstructor
@SuperBuilder
public class SuperPeriod {
    protected String status;
    protected String createdDate;
    protected String updatedDate;
}
