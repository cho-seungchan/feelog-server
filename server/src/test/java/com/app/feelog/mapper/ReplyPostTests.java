package com.app.feelog.mapper;

import com.app.feelog.domain.dto.ChannelPostReplyDTO;
import com.app.feelog.domain.vo.ChannelPostReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReplyPostTests {
    @Autowired
    private ChannelPostReplyMapper channelPostReplyMapper;

    @Test
    public void insertReply() {
        ChannelPostReplyDTO channelPostReplyDTO = new ChannelPostReplyDTO();
        channelPostReplyDTO.setReplyContent("text");
        channelPostReplyDTO.setReplyFileName("imageName");
        channelPostReplyDTO.setReplyFilePath("imagePath");

        ChannelPostReplyVO channelPostReplyVO = channelPostReplyDTO.toVO();
        channelPostReplyMapper.insertReply(channelPostReplyVO);
        log.info(channelPostReplyVO.toString());

        long generatedId = channelPostReplyVO.getId();

        channelPostReplyDTO.setMemberId(24L);
        channelPostReplyDTO.setPostId(6L);
        channelPostReplyDTO.setId(generatedId);
        channelPostReplyMapper.insertPostReply(channelPostReplyDTO.toVO());
    }
}
