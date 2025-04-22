package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryTagDTO;
import com.app.feelog.repository.DiaryTagDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class DiaryTagServiceImpl implements DiaryTagService {
    private final DiaryTagDAO diaryTagDAO;

    @Override
    public void save(DiaryTagDTO diaryTagdto) {
        diaryTagDAO.save(diaryTagdto.toVO());
    }

    @Override
    public void removeAllTagsByDiaryId(Long diaryId) {
        diaryTagDAO.deleteByDiaryId(diaryId);
    }



}