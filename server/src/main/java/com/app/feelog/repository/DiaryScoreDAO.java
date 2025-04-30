package com.app.feelog.repository;

import com.app.feelog.domain.vo.DiaryScoreVO;
import com.app.feelog.mapper.DiaryScoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class DiaryScoreDAO {

    private final DiaryScoreMapper diaryScoreMapper;

    public Optional<DiaryScoreVO> findById(Long id) {
        return Optional.ofNullable(diaryScoreMapper.findById(id));
    }

}
