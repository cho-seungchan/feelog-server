package com.app.feelog.domain.dto;

import com.app.feelog.util.pagination.AdminPagination;
import com.app.feelog.util.pagination.PostPagination;
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
public class ChannelPostListDTO {
    private List<MainPostListDTO> postList;
    private PostPagination postPagination;
}
