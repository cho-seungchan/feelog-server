package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.ChannelPostReplyDTO;
import com.app.feelog.domain.dto.ChannelPostReplyLikeDTO;
import com.app.feelog.domain.vo.ChannelPostReplyLikeVO;
import com.app.feelog.domain.vo.ChannelPostReplyVO;

import java.util.List;

public interface ChannelPostReplyLikeService {
   public void addReplyLike(ChannelPostReplyLikeDTO channelPostReplyLikeDTO);

   public default ChannelPostReplyLikeDTO toDTO(ChannelPostReplyLikeVO channelPostReplyLikeVO){
       ChannelPostReplyLikeDTO channelPostReplyLikeDTO = new ChannelPostReplyLikeDTO();
       if(channelPostReplyLikeVO != null){
           channelPostReplyLikeDTO.setId(channelPostReplyLikeVO.getId());
           channelPostReplyLikeDTO.setReplyId(channelPostReplyLikeVO.getId());
           channelPostReplyLikeDTO.setCreatedDate(channelPostReplyLikeVO.getCreatedDate());
           channelPostReplyLikeDTO.setUpdatedDate(channelPostReplyLikeVO.getUpdatedDate());
           channelPostReplyLikeDTO.setMemberId(channelPostReplyLikeVO.getMemberId());
       }
       return channelPostReplyLikeDTO;
   }
}
