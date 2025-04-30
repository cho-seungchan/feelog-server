package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelDTO;
import com.app.feelog.domain.dto.ChannelSearchDTO;
import com.app.feelog.repository.ChannelDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

    private final ChannelDAO channelDAO;

    @Override
    public List<ChannelSearchDTO> searchChannels(String keyword) {
        return channelDAO.searchChannels(keyword);
    }

    @Override
    public List<ChannelSearchDTO> searchMoreChannels(String keyword, int limit, int offset) {
        return channelDAO.searchMoreChannels(keyword, limit, offset);
    }

    @Override
    public Long findChannelOwnerId(Long channelId) {
        return channelDAO.findChannelOwnerId(channelId);
    }

    @Override
    public Optional<ChannelDTO> getMyChannel(Long memberId) {
        return channelDAO.findByMemberId(memberId)
                .map(this::toDTO);
    }

}
