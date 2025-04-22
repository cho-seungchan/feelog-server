package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostTagDTO;

public interface ChannelPostTagService {

    void save(ChannelPostTagDTO dto);

    void removeAllTagsByChannelPostId(Long channelPostId);

}
