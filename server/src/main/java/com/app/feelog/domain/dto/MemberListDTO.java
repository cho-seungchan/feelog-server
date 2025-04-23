package com.app.feelog.domain.dto;

import com.app.feelog.util.pagination.MemberPagination;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
public class MemberListDTO {
    private List<MemberDTO> memberList;
    private MemberPagination memberPagination;
}
