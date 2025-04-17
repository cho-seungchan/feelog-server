package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.NoticeStatus;
import com.app.feelog.domain.vo.NoticeVO;
import com.app.feelog.domain.vo.Period;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NoticeDTO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private Long memberId;
    private NoticeStatus noticeStatus;
    private String createdDate;
    private String updatedDate;

    public NoticeVO toVO() {
        return NoticeVO.builder()
                .id(id)
                .noticeTitle(noticeTitle)
                .noticeContent(noticeContent)
                .memberId(memberId)
                .noticeStatus(noticeStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
