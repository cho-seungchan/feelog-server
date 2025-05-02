package com.app.feelog.mapper;

import com.app.feelog.domain.dto.DiaryPreviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiaryPreviewMapper {

    // 05 01
    List<DiaryPreviewDTO> findVisibleDiariesByChannelUrl(
            @Param("viewerId") Long viewerId,
            @Param("channelUrl") String channelUrl
    );

    // 05 01
    List<DiaryPreviewDTO> findVisibleDiariesWithPaginationByChannelUrl(
            @Param("viewerId") Long viewerId,
            @Param("channelUrl") String channelUrl,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    int countVisibleDiaries(@Param("channelUrl") String channelUrl, @Param("viewerId") Long viewerId);
}
