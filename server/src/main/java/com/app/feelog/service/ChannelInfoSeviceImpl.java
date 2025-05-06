package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelInfoDTO;
import com.app.feelog.repository.ChannelInfoDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelInfoSeviceImpl implements ChannelInfoService {

    private final ChannelInfoDAO channelInfoDAO;

    @Override
    public ChannelInfoDTO findInfoByUrl(String channelUrl) {
        return channelInfoDAO.findInfoByUrl(channelUrl);
    }
}
