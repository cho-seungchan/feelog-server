package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostDTO;
import com.app.feelog.domain.dto.SubscribeDTO;
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

    @Override
    public boolean toggleSubscribe(Long memberId, Long channelId) {
        SubscribeDTO existing = subscribeDAO.findByMemberAndChannel(memberId, channelId);

        if (existing != null) {
            if ("정상".equals(existing.getSubscribeStatus())) {
                subscribeDAO.updateSubscribeStatus(memberId, channelId, "해제");
                return false; // 구독 취소
            } else {
                subscribeDAO.updateSubscribeStatus(memberId, channelId, "정상");
                return true; // 다시 구독
            }
        } else {
            subscribeDAO.insertSubscribe(memberId, channelId);
            return true; // 신규 구독
        }
    }

    @Override
    public Long findChannelOwnerId(Long channelId) {
        return subscribeDAO.findChannelOwnerId(channelId);
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
