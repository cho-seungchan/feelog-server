package com.app.feelog.repository;

import com.app.feelog.domain.vo.ChallengeDiaryVO;
import com.app.feelog.mapper.ChallengeDiaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChallengeDiaryDAO {

    private final ChallengeDiaryMapper challengeDiaryMapper;

    public void save(ChallengeDiaryVO challengeDiaryVO) {
        challengeDiaryMapper.insert(challengeDiaryVO);
    }

    public Optional<ChallengeDiaryVO> findById(Long id) {
        return challengeDiaryMapper.findById(id);
    }

}
