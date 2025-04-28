package com.app.feelog.service;


import com.app.feelog.domain.enumeration.SubscribeStatus;
import com.app.feelog.domain.dto.SubscribeDTO;
import com.app.feelog.domain.vo.SubscribeVO;
import java.util.Optional;

public interface SubscribeService {

    // 구독 여부 확인
    boolean isSubscribed(Long memberId, Long channelId);

    // 구독
    void subscribe(Long memberId, Long channelId);

    // 구독 취소
    void unsubscribe(Long memberId, Long channelId);

    public default SubscribeDTO toDTO(SubscribeVO vo){
        SubscribeDTO dto = new SubscribeDTO();
        if(vo != null){
            dto.setId(vo.getId());
            dto.setMemberId(vo.getMemberId());
            dto.setChannelId(vo.getChannelId());
            dto.setSubscribeStatus(vo.getSubscribeStatus());
            dto.setCreatedDate(vo.getCreatedDate());
            dto.setUpdatedDate(vo.getUpdatedDate());
        }

        return dto;
    }

    public Optional<SubscribeDTO> getSubscribeOne(Long memberId, Long channelId);

    public void deleteSubscribe(Long memberId, Long channelId);
}
