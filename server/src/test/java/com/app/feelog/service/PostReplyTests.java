package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostReplyDTO;
import com.app.feelog.domain.dto.ChannelPostReplyLikeDTO;
import com.app.feelog.domain.dto.ChannelPostReplyReportDTO;
import com.app.feelog.service.voToDto.ChannelPostReplyLikeService;
import com.app.feelog.service.voToDto.ChannelPostReplyReportService;
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
    @Autowired
    private ChannelPostReplyLikeService channelPostReplyLikeService;
    @Autowired
    private ChannelPostReplyReportService channelPostReplyReportService;

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

    @Test
    public void likeInsert () {
        ChannelPostReplyLikeDTO channelPostReplyLikeDTO = new ChannelPostReplyLikeDTO();
        channelPostReplyLikeDTO.setReplyId(19L);
        channelPostReplyLikeDTO.setMemberId(24L);
        channelPostReplyLikeService.addReplyLike(channelPostReplyLikeDTO);
    }

    @Test
    public void reportInsert () {
        ChannelPostReplyReportDTO channelPostReplyReportDTO = new ChannelPostReplyReportDTO();
        channelPostReplyReportDTO.setReplyId(9L);
        channelPostReplyReportDTO.setMemberId(21L);
        channelPostReplyReportService.addReplyReport(channelPostReplyReportDTO);
    }
}
