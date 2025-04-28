package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostReplyDTO;
import com.app.feelog.service.voToDto.ChannelPostReplyService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PostReplyTests {
    @Autowired
    private ChannelPostReplyService channelPostReplyService;

    @Test
    public void addPostReply() {
        ChannelPostReplyDTO channelPostReplyDTO = new ChannelPostReplyDTO();
        channelPostReplyDTO.setReplyContent("text");
        channelPostReplyDTO.setReplyFileName("image.jpg");
        channelPostReplyDTO.setReplyFilePath("image.jpg");
        channelPostReplyDTO.setPostId(6L);
        channelPostReplyDTO.setMemberId(23L);
        channelPostReplyService.addPostReply(channelPostReplyDTO);
    }
}
