package com.app.feelog.repository;

import com.app.feelog.domain.vo.DiaryVO;
import com.app.feelog.mapper.DiaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DiaryDAO {
    private final DiaryMapper diaryMapper;

    public void save(DiaryVO diaryVO){
        diaryMapper.insert(diaryVO);
    }

    public Optional<DiaryVO> findById(Long id) {
        return diaryMapper.selectById(id);
    }

    public void update(DiaryVO diaryVO) {
        diaryMapper.update(diaryVO);
    }

}
