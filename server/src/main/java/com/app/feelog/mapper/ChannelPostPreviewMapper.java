package com.app.feelog.mapper;

import com.app.feelog.domain.dto.ChannelPostPreviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChannelPostPreviewMapper {

    // 포스트 슬라이드용
    List<ChannelPostPreviewDTO> findPostSlides(@Param("channelId") Long channelId);

    // 응원글 슬라이드용
    List<ChannelPostPreviewDTO> findCheerSlides(@Param("channelId") Long channelId);

    List<ChannelPostPreviewDTO> findPostsWithPagination(@Param("channelId") Long channelId,
                                                        @Param("offset") int offset,
                                                        @Param("limit") int limit);

    int countPosts(@Param("channelId") Long channelId);

    List<ChannelPostPreviewDTO> findCheersWithPagination(@Param("channelId") Long channelId,
                                                         @Param("offset") int offset,
                                                         @Param("limit") int limit);

    int countCheers(@Param("channelId") Long channelId);
}
