package com.app.feelog.mapper;

import com.app.feelog.domain.dto.ChannelInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChannelInfoMapper {

    ChannelInfoDTO findInfoByUrl(String channelUrl);

}
