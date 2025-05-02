package com.app.feelog.repository;

import com.app.feelog.domain.dto.ChannelInfoDTO;
import com.app.feelog.mapper.ChannelInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChannelInfoDAO {

    private final ChannelInfoMapper channelInfoMapper;

    public ChannelInfoDTO findInfoByUrl(String channelUrl) {
        return channelInfoMapper.findInfoByUrl(channelUrl);
    }

}
