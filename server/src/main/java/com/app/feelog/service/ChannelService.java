package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelDTO;
import com.app.feelog.domain.dto.ChannelSearchDTO;
import com.app.feelog.domain.vo.ChannelVO;

import java.util.List;
import java.util.Optional;

public interface ChannelService {

    public List<ChannelSearchDTO> searchChannels(String keyword);

    List<ChannelSearchDTO> searchMoreChannels(String keyword, int limit, int offset);

    Long findChannelOwnerId(Long channelId);

    public Optional<ChannelDTO> getMyChannel(Long memberId);

    default ChannelDTO toDTO(ChannelVO vo) {
        ChannelDTO dto = new ChannelDTO();
        dto.setId(vo.getId());
        dto.setChannelTitle(vo.getChannelTitle());
        dto.setChannelIntroduce(vo.getChannelIntroduce());
        dto.setChannelUrl(vo.getChannelUrl());
        dto.setChannelFilePath(vo.getChannelFilePath());
        dto.setChannelFileName(vo.getChannelFileName());
        dto.setChannelStatus(vo.getChannelStatus());
        dto.setMemberId(vo.getMemberId());
        dto.setCreatedDate(vo.getCreatedDate());
        dto.setUpdatedDate(vo.getUpdatedDate());
        return dto;
    }
}
