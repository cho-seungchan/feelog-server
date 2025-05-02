package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostReplyDTO;
import com.app.feelog.domain.vo.ChannelPostReplyVO;
import com.app.feelog.repository.ChannelPostReplyDAO;
import com.app.feelog.repository.ChannelPostReplyLikeDAO;
import com.app.feelog.service.voToDto.ChannelPostReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChannelPostReplyServiceImpl implements ChannelPostReplyService {
    private final ChannelPostReplyDAO channelPostReplyDAO;
    private final ChannelPostReplyLikeDAO channelPostReplyLikeDAO;

    @Override
    public void addPostReply(ChannelPostReplyDTO channelPostReplyDTO) {
        ChannelPostReplyVO channelPostReplyVO = channelPostReplyDTO.toVO();

        channelPostReplyDAO.saveReply(channelPostReplyVO);
        channelPostReplyDTO.setId(channelPostReplyVO.getId());
        channelPostReplyDAO.savePostReply(channelPostReplyVO);
    }

    @Override
    public List<ChannelPostReplyDTO> getReplyByPostId(Long id) {
        List<ChannelPostReplyDTO> postReplys = channelPostReplyDAO.findReplyByPostId(id);

        postReplys.forEach((post)->{
            post.setReplyLikeCount(channelPostReplyLikeDAO.findReplyLikeCount(post.getId()));
        });

        return postReplys;
    }

    @Override
    public List<Long> getIsLiked(Long memberId) {
        return channelPostReplyDAO.findIsLiked(memberId);
    }
}
