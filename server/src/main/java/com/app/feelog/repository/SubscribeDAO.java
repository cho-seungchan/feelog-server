package com.app.feelog.repository;

import com.app.feelog.domain.dto.SubscribeDTO;
import com.app.feelog.mapper.SubscribeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SubscribeDAO {

    private final SubscribeMapper subscribeMapper;

    public SubscribeDTO findByMemberAndChannel(Long memberId, Long channelId) {
        return subscribeMapper.findByMemberAndChannel(memberId, channelId);
    }

    public void insertSubscribe(Long memberId, Long channelId) {
        subscribeMapper.insertSubscribe(channelId, memberId);
    }

    public void updateSubscribeStatus(Long memberId, Long channelId, String status) {
        subscribeMapper.updateSubscribeStatus(memberId, channelId, status);
    }

    public Long findChannelOwnerId(Long channelId) {
        return subscribeMapper.findChannelOwnerId(channelId);
    }

}
