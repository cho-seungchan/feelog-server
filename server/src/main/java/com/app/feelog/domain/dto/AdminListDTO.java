package com.app.feelog.domain.dto;

import com.app.feelog.util.pagination.AdminPagination;
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
public class AdminListDTO {
    private List<MemberDTO> adminList;
    private AdminPagination adminPagination;
}
