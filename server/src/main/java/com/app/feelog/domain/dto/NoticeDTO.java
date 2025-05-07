package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.NoticeStatus;
import com.app.feelog.domain.vo.NoticeVO;
import com.app.feelog.domain.vo.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
    private String noticeFilePath;
    private String noticeFileName;
    private String createdDate;
    private String updatedDate;
    private int readCount;
    private boolean isSubscribed;

    public NoticeVO toVO() {
        return NoticeVO.builder()
                .id(id)
                .noticeTitle(noticeTitle)
                .noticeContent(noticeContent)
                .memberId(memberId)
                .noticeStatus(noticeStatus)
                .noticeFilePath(noticeFilePath)
                .noticeFileName(noticeFileName)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .readCount(readCount)
                .build();
    }
}
