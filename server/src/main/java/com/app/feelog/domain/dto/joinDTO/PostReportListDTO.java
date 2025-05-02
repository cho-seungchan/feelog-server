package com.app.feelog.domain.dto.joinDTO;

import com.app.feelog.domain.enumeration.PostStatus;
import com.app.feelog.domain.enumeration.ReportStatus;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PostReportListDTO {
    private Long id;
    private String postTitle;
    private String updatedDate;
    private ReportStatus reportStatus;
    private Long postId;
}
