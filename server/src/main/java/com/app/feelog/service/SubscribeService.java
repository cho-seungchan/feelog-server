package com.app.feelog.service;

import com.app.feelog.domain.dto.SubscribeDTO;
import com.app.feelog.domain.vo.SubscribeVO;

import java.util.Optional;

public interface SubscribeService {

    boolean toggleSubscribe(Long memberId, Long channelId);

    Long findChannelOwnerId(Long channelId);

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
