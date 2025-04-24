package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelSearchDTO;

import java.util.List;

public interface ChannelService {

    public List<ChannelSearchDTO> searchChannels(String keyword);

}
