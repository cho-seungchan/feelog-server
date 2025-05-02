package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelInfoDTO;

public interface ChannelInfoService {

    ChannelInfoDTO findInfoByUrl(String channelUrl);

}
