package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.ChannelPostListDTO;
import com.app.feelog.domain.dto.ChannelPostScrapDTO;
import com.app.feelog.domain.vo.ChannelPostScrapVO;

import java.util.List;

public interface ChannelPostScrapService {
    public void insertScrap(ChannelPostScrapDTO channelPostScrapDTO);

    public List<ChannelPostScrapDTO> getScrapByMemberId(Long id);

    public void deleteScrapByPostId(Long id);

    public default ChannelPostScrapDTO toChannelPostScrapDTO(ChannelPostScrapVO channelPostScrapVO) {
        ChannelPostScrapDTO channelPostScrapDTO = new ChannelPostScrapDTO();
        if(channelPostScrapVO != null) {
            channelPostScrapDTO.setId(channelPostScrapVO.getId());
            channelPostScrapDTO.setPostId(channelPostScrapVO.getPostId());
            channelPostScrapDTO.setMemberId(channelPostScrapVO.getMemberId());
            channelPostScrapDTO.setChannelPostScrapStatus(channelPostScrapVO.getChannelPostScrapStatus());
            channelPostScrapDTO.setCreatedDate(channelPostScrapVO.getCreatedDate());
            channelPostScrapDTO.setUpdatedDate(channelPostScrapVO.getUpdatedDate());
        }
        return channelPostScrapDTO;
    }
}
