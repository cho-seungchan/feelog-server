package com.app.feelog.service;

public interface SubscribeService {

    boolean toggleSubscribe(Long memberId, Long channelId);

    Long findChannelOwnerId(Long channelId);

}
