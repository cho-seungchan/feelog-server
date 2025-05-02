package com.app.feelog.mapper;

import com.app.feelog.domain.dto.OfficialContentPreviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OfficialContentPreviewMapper {

    List<OfficialContentPreviewDTO> findTop5ByContentType(@Param("type") String type);

    List<OfficialContentPreviewDTO> findNoticePage(@Param("limit") int limit, @Param("offset") int offset);

    int countNotices();
}
