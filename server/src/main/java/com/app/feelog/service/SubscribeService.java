package com.app.feelog.service;

import com.app.feelog.domain.enumeration.SubscribeStatus;

public interface SubscribeService {

    // 구독 여부 확인
    boolean isSubscribed(Long memberId, Long channelId);

    // 구독
    void subscribe(Long memberId, Long channelId);

    // 구독 취소
    void unsubscribe(Long memberId, Long channelId);

}
