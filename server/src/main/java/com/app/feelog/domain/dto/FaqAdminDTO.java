package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.NoticeStatus;
import com.app.feelog.domain.vo.Period;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Setter
public class FaqAdminDTO extends Period {
    private Long id;
    private String faqTitle;
    private String faqContent;
    private Long memberId;
    private NoticeStatus faqStatus;
    private String createdDate;
    private String updatedDate;
    private String memberNickname;
}
