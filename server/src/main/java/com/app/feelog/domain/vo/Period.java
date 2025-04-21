package com.app.feelog.domain.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter @ToString
@NoArgsConstructor
public class Period {
    protected String status;
    protected String createdDate;
    protected String updatedDate;

    public Period(String createdDate, String updatedDate) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
