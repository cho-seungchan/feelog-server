package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.ChannelPostReplyDTO;
import com.app.feelog.domain.vo.ChannelPostReplyVO;

import java.util.List;

public interface ChannelPostReplyService {
    public void addPostReply(ChannelPostReplyDTO channelPostReplyDTO);

    public List<ChannelPostReplyDTO> getReplyByPostId(Long id);

    public List<Long> getIsLiked(Long memberId);

    public default ChannelPostReplyDTO toDTO(ChannelPostReplyVO channelPostReplyVO) {
        ChannelPostReplyDTO channelPostReplyDTO = new ChannelPostReplyDTO();
        if(channelPostReplyVO != null){
            channelPostReplyDTO.setId(channelPostReplyVO.getId());
            channelPostReplyDTO.setPostId(channelPostReplyVO.getPostId());
            channelPostReplyDTO.setMemberId(channelPostReplyVO.getMemberId());
            channelPostReplyDTO.setReplyFilePath(channelPostReplyVO.getReplyFilePath());
            channelPostReplyDTO.setReplyFileName(channelPostReplyVO.getReplyFileName());
            channelPostReplyDTO.setReplyContent(channelPostReplyVO.getReplyContent());
            channelPostReplyDTO.setReplyStatus(channelPostReplyVO.getReplyStatus());
        }

        return channelPostReplyDTO;
    }
}
