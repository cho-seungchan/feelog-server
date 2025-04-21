package com.app.feelog.repository;

import com.app.feelog.domain.vo.DiaryTagVO;
import com.app.feelog.mapper.DiaryTagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiaryTagDAO {

    private final DiaryTagMapper diaryTagMapper;

    // 다이어리에 태그 연결
    public void save(DiaryTagVO diaryTagVO) {
        diaryTagMapper.insertDiaryTag(diaryTagVO);
    }

    public List<String> findTagContentsByDiaryId(Long diaryId) {
        return diaryTagMapper.selectTagContentsByDiaryId(diaryId);
    }

    public void deleteByDiaryId(Long diaryId) {
        diaryTagMapper.deleteByDiaryId(diaryId);
    }

}
