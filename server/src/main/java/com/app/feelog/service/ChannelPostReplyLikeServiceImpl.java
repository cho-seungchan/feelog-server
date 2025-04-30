package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostReplyLikeDTO;
import com.app.feelog.domain.vo.ChannelPostReplyLikeVO;
import com.app.feelog.repository.ChannelPostReplyLikeDAO;
import com.app.feelog.service.voToDto.ChannelPostReplyLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChannelPostReplyLikeServiceImpl implements ChannelPostReplyLikeService {
    private final ChannelPostReplyLikeDAO channelPostReplyLikeDAO;

    @Override
    public void addReplyLike(ChannelPostReplyLikeDTO channelPostReplyLikeDTO) {
        ChannelPostReplyLikeVO channelPostReplyLikeVO = channelPostReplyLikeDTO.toVO();
        ChannelPostReplyLikeVO checkLike = channelPostReplyLikeDAO.findReplyLikeByReplyId(channelPostReplyLikeVO.getReplyId(), channelPostReplyLikeVO.getMemberId());

        if(checkLike == null){
            channelPostReplyLikeDAO.saveSuperLike(channelPostReplyLikeVO);
            channelPostReplyLikeDAO.saveReplyLike(channelPostReplyLikeVO);
        }else {
            channelPostReplyLikeDAO.deleteReplyLike(channelPostReplyLikeVO.getReplyId(), channelPostReplyLikeVO.getMemberId());
        }
    }


}
