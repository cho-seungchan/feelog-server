package com.app.feelog.service;

import com.app.feelog.domain.dto.*;
import com.app.feelog.repository.MemberDAO;
import com.app.feelog.service.voToDto.ChannelPostScrapService;
import com.app.feelog.util.pagination.AdminPagination;
import com.app.feelog.util.pagination.MemberPagination;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class ScrapServiceImplTests {
    @Autowired
    private ChannelPostScrapService channelPostScrapService;
    @Autowired
    private ChannelPostService channelPostService;
    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void selectTest () {
        List<ChannelPostScrapDTO> scraps = channelPostScrapService.getScrapByMemberId(24L);
        scraps.forEach(System.out::println);
    }

    @Test
    public void insert2() {
        ChannelPostReportListDTO reportList = new ChannelPostReportListDTO();

        channelPostService.addReport(reportList);

        reportList.setReportMemberId(23L);
        reportList.setPostId(9L);

        channelPostService.addChannelPostReport(reportList);
    }

    @Test
    public void getNextPost() {
        Optional<ChannelPostDTO> post = channelPostService.getNextPost(1L, 6L);
        post.ifPresent(System.out::println);
    }

    @Test
    public void getPreviousPost() {
        Optional<ChannelPostDTO> post = channelPostService.getPreviousPost(1L, 6L);
        post.ifPresent(System.out::println);
    }
}
