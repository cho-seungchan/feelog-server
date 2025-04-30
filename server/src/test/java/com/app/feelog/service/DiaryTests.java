package com.app.feelog.service;

import com.app.feelog.domain.dto.joinDTO.DiaryJoinDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryPaginationDTO;
import com.app.feelog.domain.enumeration.DiaryOpen;
import com.app.feelog.mapper.DiaryMapper;
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


}
