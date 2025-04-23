package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostTagDTO;

import java.util.List;

public interface ChannelPostTagService {

    void save(ChannelPostTagDTO dto);

    void removeAllTagsByChannelPostId(Long channelPostId);

    List<String> findTagContentsByChannelPostId(Long channelPostId);

    void reactivateTagStatus(Long tagId, Long channelPostId);

}
