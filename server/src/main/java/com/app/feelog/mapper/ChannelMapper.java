package com.app.feelog.mapper;

import com.app.feelog.domain.dto.ChannelSearchDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChannelMapper {

    List<ChannelSearchDTO> searchChannels(String keyword);

    List<ChannelSearchDTO> searchMoreChannels(@Param("keyword") String keyword, @Param("limit") int limit, @Param("offset") int offset);

    Long findChannelOwnerId(Long channelId);

}
