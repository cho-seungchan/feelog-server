package com.app.feelog.service;

import com.app.feelog.domain.dto.joinDTO.DiaryDetailDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryJoinDTO;
import com.app.feelog.mapper.DiaryMapper;
import com.app.feelog.mapper.DiaryReplyLikeMapper;
import com.app.feelog.mapper.DiaryReplyReportMapper;
import com.app.feelog.mapper.SubscribeMapper;
import com.app.feelog.repository.DiaryDAO;
import com.app.feelog.service.voToDto.ReportService;
import com.app.feelog.util.pagination.PostPagination;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class DiaryTests {
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private DiaryMapper diaryMapper;
    @Autowired
    private SubscribeService subscribeService;
    @Autowired
    private SubscribeMapper subscribeMapper;
    @Autowired
    private DiaryReplyLikeMapper diaryReplyLikeMapper;
    @Autowired
    private ReportService reportService;
    @Autowired
    private DiaryReplyReportMapper diaryReplyReportMapper;
    @Autowired
    private DiaryDAO diaryDAO;


    @Test
    public void diaryReportIds() {
        PostPagination postPagination = new PostPagination();

        postPagination.setPage(1);
        postPagination.create(diaryMapper.selectDiaryCountAllAndSubscribe(24L));
        log.info("offset {}", postPagination.getOffset());
        log.info("rowCount {}", postPagination.getRowCount());

        List<DiaryJoinDTO> diarys = diaryMapper.selectDiaryListPaginationAllAndSubscribe(postPagination, 24L);

        diarys.forEach(System.out::println);
    }

    @Test
    public void subscribeIds() {
        List<Long> ids = subscribeMapper.selectSubscribeIdsByMemberId(24L);
        log.info("ids : {}", ids);
    }

    @Test
    public void diaryOpen() {
        DiaryDetailDTO diary = diaryService.getDiaryDetailByDiaryId(15L);
        log.info("diary : {}", diary);
    }

    @Test
    public void allSubscribe() {
        PostPagination postPagination = new PostPagination();
        postPagination.setPage(2);
        postPagination.create(diaryMapper.selectDiaryCountAll());
        List<DiaryJoinDTO> diaryList = diaryMapper.selectDiaryListPaginationAll(postPagination);

        diaryList.forEach(System.out::println);
    }

    @Test
    public void selectDiaryREplyLike() {
        log.info("count {}", diaryDAO.findDiaryReplyCount(13L));
    }
}
