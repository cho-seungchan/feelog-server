package com.app.feelog.mapper;

import com.app.feelog.domain.dto.FaqAdminDTO;
import com.app.feelog.domain.dto.NoticeAdminDTO;
import com.app.feelog.domain.dto.NoticeDTO;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class FaqMapperTests {
    @Autowired
    private FaqMapper faqMapper;
    @Autowired
    private FaqAdminDTO faqAdminDTO;

    @Test
    public void selectNoticeTest () {
        NoticePagination noticePagination = new NoticePagination();

        noticePagination.setPage(1);
        noticePagination.create(faqMapper.faqCount());
        log.info(noticePagination.toString());

        List<FaqAdminDTO> faqList = faqMapper.selectFaqAll(noticePagination);
        faqList.forEach(faqVO -> {log.info(faqVO.toString());});
    }
}
