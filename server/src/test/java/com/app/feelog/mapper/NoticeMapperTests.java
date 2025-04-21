package com.app.feelog.mapper;

import com.app.feelog.domain.dto.NoticeDTO;
import com.app.feelog.domain.vo.NoticeVO;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class NoticeMapperTests {
    @Autowired
    private NoticeMapper noticeMapper;

    @Test
    public void insertNotice() {
        NoticeDTO notice = new NoticeDTO();

        notice.setNoticeTitle("test");
        notice.setNoticeContent("testContent");
        notice.setMemberId(2L);

        noticeMapper.insertNotice(notice.toVO());
    }

    @Test
    public void selectNoticeTest () {
        NoticePagination noticePagination = new NoticePagination();

        noticePagination.setPage(1);
        noticePagination.create(noticeMapper.selectNoticeCount());
        log.info(noticePagination.toString());

        List<NoticeVO> noticeList = noticeMapper.selectNoticeAll(noticePagination);
        noticeList.forEach(noticeVO -> {log.info(noticeVO.toString());});
    }
}
