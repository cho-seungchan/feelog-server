package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelSearchDTO;
import com.app.feelog.repository.ChannelDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

    private final ChannelDAO channelDAO;

    @Override
    public List<ChannelSearchDTO> searchChannels(String keyword) {
        return channelDAO.searchChannels(keyword);
    }

}
