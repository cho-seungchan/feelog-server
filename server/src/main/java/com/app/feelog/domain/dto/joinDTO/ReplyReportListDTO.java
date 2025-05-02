package com.app.feelog.domain.dto.joinDTO;

import com.app.feelog.domain.enumeration.DiaryStatus;
import com.app.feelog.domain.enumeration.ReplyStatus;
import com.app.feelog.domain.enumeration.ReportStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ReplyReportListDTO {
    private Long id;
    private String replyContent;
    private String updatedDate;
    private ReportStatus reportStatus;
    private Long postId;
}
