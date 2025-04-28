package com.app.feelog.repository;

import com.app.feelog.domain.dto.SubscribeDTO;
import com.app.feelog.domain.vo.SubscribeVO;
import com.app.feelog.mapper.SubscribeMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    public Optional<SubscribeVO> findSubscribeOne(@Param("memberId") Long memberId, @Param("channelId") Long channelId){
        return subscribeMapper.selectSubscribeOne(memberId, channelId);
    };

    public int findSubscribeCount(Long channelId){
        return subscribeMapper.selectSubscribeCount(channelId);
    };

    public void deleteSubscribe(@Param("memberId") Long memberId, @Param("channelId") Long channelId){
        subscribeMapper.deleteSubscribe(memberId, channelId);
    };


}
