package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.NoticeStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class NoticeVO extends Period{
    @EqualsAndHashCode.Include
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private Long memberId;
    private NoticeStatus noticeStatus;

    @Builder

    public NoticeVO(String createdDate, String updatedData, Long id, String noticeTitle, String noticeContent, Long memberId, NoticeStatus noticeStatus) {
        super(createdDate, updatedData);
        this.id = id;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.memberId = memberId;
        this.noticeStatus = noticeStatus;
    }
}
