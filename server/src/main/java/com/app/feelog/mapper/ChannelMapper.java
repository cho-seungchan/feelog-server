package com.app.feelog.mapper;

import com.app.feelog.domain.dto.ChannelSearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChannelMapper {

    List<ChannelSearchDTO> searchChannels(String keyword);

}
