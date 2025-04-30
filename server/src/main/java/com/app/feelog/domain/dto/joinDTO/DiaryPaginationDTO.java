package com.app.feelog.domain.dto.joinDTO;

import com.app.feelog.util.pagination.PostPagination;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DiaryPaginationDTO {
    private List<DiaryJoinDTO> diaryList;
    private List<Long> reportIds;
    private PostPagination postPagination;
}
