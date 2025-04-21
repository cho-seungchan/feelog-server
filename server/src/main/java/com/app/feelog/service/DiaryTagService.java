package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryTagDTO;

public interface DiaryTagService {
    void save(DiaryTagDTO diaryTagdto);

    void removeAllTagsByDiaryId(Long diaryId);
}
