package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelSearchDTO;

import java.util.List;

public interface ChannelService {

    public List<ChannelSearchDTO> searchChannels(String keyword);

    List<ChannelSearchDTO> searchMoreChannels(String keyword, int limit, int offset);

    Long findChannelOwnerId(Long channelId);

}
