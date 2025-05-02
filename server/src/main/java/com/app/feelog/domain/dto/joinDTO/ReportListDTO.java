package com.app.feelog.domain.dto.joinDTO;

import com.app.feelog.util.Pagination;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReportListDTO {
    private NoticePagination pagination;
    private List<DiaryReportListDTO> diaryReportList;
    private List<ReplyReportListDTO> replyReportList;
    private List<PostReportListDTO> postReportList;
}
