package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostReplyDTO;
import com.app.feelog.domain.vo.ChannelPostReplyVO;
import com.app.feelog.repository.ChannelPostReplyDAO;
import com.app.feelog.service.voToDto.ChannelPostReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChannelPostReplyServiceImpl implements ChannelPostReplyService {
    private final ChannelPostReplyDAO channelPostReplyDAO;

    @Override
    public void addPostReply(ChannelPostReplyDTO channelPostReplyDTO) {
        ChannelPostReplyVO channelPostReplyVO = channelPostReplyDTO.toVO();

        channelPostReplyDAO.saveReply(channelPostReplyVO);
        channelPostReplyDAO.savePostReply(channelPostReplyVO);
    }
}
