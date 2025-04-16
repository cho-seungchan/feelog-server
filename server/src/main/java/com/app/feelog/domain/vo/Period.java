package com.app.feelog.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @ToString
@NoArgsConstructor
public class Period {
    protected String status;
    protected String createdDate;
    protected String updatedData;

    public Period(String createdDate, String updatedData) {
        this.createdDate = createdDate;
        this.updatedData = updatedData;
    }
}
