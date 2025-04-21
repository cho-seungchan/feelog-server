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
public class NoticeAdminDTO extends Period {
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private Long memberId;
    private NoticeStatus noticeStatus;
    private String createdDate;
    private String updatedDate;
    private String memberNickname;
}
