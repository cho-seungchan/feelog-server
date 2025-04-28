package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostDTO;
import com.app.feelog.domain.dto.SubscribeDTO;
import com.app.feelog.domain.enumeration.SubscribeStatus;
import com.app.feelog.domain.vo.SubscribeVO;
import com.app.feelog.repository.SubscribeDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SubscribeServiceImpl implements SubscribeService {

    private final SubscribeDAO subscribeDAO;
    private final NotificationService notificationService;

    @Override
    public boolean isSubscribed(Long memberId, Long channelId) {
        return subscribeDAO.selectIsSubscribed(memberId, channelId);
    }

    @Override
    public void subscribe(Long memberId, Long channelId) {

        Long subscribeId = subscribeDAO.insertSubscribe(memberId, channelId);

        notificationService.sendSubscribeNotification(memberId, channelId, subscribeId);
    }

    @Override
    public void unsubscribe(Long memberId, Long channelId) {
        subscribeDAO.deleteSubscribe(memberId, channelId);
    }

    @Override
    public Optional<SubscribeDTO> getSubscribeOne(Long memberId, Long channelId) {
        return Optional.ofNullable(toDTO(subscribeDAO.findSubscribeOne(memberId, channelId).orElse(null)));
    }

    @Override
    public void deleteSubscribe(Long memberId, Long channelId) {
        subscribeDAO.deleteSubscribe(memberId, channelId);
    }
}
