package com.app.feelog.repository;

import com.app.feelog.domain.dto.ChannelSearchDTO;
import com.app.feelog.mapper.ChannelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChannelDAO {

    private final ChannelMapper channelMapper;

    public List<ChannelSearchDTO> searchChannels(String keyword) {
        return channelMapper.searchChannels(keyword);
    }

    public List<ChannelSearchDTO> searchMoreChannels(String keyword, int limit, int offset) {
        return channelMapper.searchMoreChannels(keyword, limit, offset);
    }

    public Long findChannelOwnerId(Long channelId) {
        return channelMapper.findChannelOwnerId(channelId);
    }

}
