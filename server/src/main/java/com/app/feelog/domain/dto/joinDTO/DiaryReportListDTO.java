package com.app.feelog.domain.dto.joinDTO;

import com.app.feelog.domain.enumeration.DiaryStatus;
import com.app.feelog.domain.enumeration.PostStatus;
import com.app.feelog.domain.enumeration.ReportStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DiaryReportListDTO {
    private Long id;
    private String diaryTitle;
    private String updatedDate;
    private ReportStatus reportStatus;
    private Long diaryId;
}
