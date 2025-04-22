package com.app.feelog.mapper;

import com.app.feelog.domain.vo.ChallengeDiaryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ChallengeDiaryMapper {

    void insert(ChallengeDiaryVO challengeDiaryVO);

    Optional<ChallengeDiaryVO> findById(Long id);

}
