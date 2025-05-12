package com.app.feelog.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FindJopDTO {
    private String originNm;
    private String visaNm;
    private String lang;
    private String careerStyle;
    private String title;
    private String endDate;
    private String startDate;
    private String nation;
    private String companyName;
    private String industry;
    private int recruitCount;

    // 명시적으로 매개변수를 받는 생성자 추가
    public FindJopDTO(String originNm, String visaNm, String lang, String careerStyle, String title, String endDate,
                      String startDate, String nation, String companyName, String industry, int recruitCount) {
        this.originNm = originNm;
        this.visaNm = visaNm;
        this.lang = lang;
        this.careerStyle = careerStyle;
        this.title = title;
        this.endDate = endDate;
        this.startDate = startDate;
        this.nation = nation;
        this.companyName = companyName;
        this.industry = industry;
        this.recruitCount = recruitCount;
    }
}
