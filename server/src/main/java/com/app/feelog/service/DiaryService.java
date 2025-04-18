package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryDTO;
import com.app.feelog.domain.vo.DiaryVO;
import com.app.feelog.repository.DiaryDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class DiaryService {
    private final DiaryDAO diaryDAO;

    public Long writeDiary(DiaryDTO diaryDTO) {
        DiaryVO diaryVO = diaryDTO.toVO();
        diaryDAO.save(diaryVO);
        return diaryVO.getId();
    }

}
