package com.app.feelog.repository;

import com.app.feelog.domain.dto.SubscribeDTO;
import com.app.feelog.domain.enumeration.SubscribeStatus;
import com.app.feelog.mapper.SubscribeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SubscribeDAO {

    private final SubscribeMapper subscribeMapper;

    // 1. 구독 여부 확인
    public boolean selectIsSubscribed(Long memberId, Long channelId) {
        return subscribeMapper.isSubscribed(memberId, channelId);
    }

    // 2. 구독
    public Long insertSubscribe(Long memberId, Long channelId) {
        SubscribeDTO subscribeDTO = new SubscribeDTO();
        subscribeDTO.setMemberId(memberId);
        subscribeDTO.setChannelId(channelId);

        subscribeMapper.subscribe(subscribeDTO);

        return subscribeDTO.getId(); // 여기서 id를 받아서 리턴!!
    }

    // 3. 구독 취소
    public void deleteSubscribe(Long memberId, Long channelId) {
        subscribeMapper.unsubscribe(memberId, channelId);
    }

}
