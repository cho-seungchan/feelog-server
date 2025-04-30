package com.app.feelog.mapper;

import com.app.feelog.domain.vo.DiaryScoreVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiaryScoreMapper {

    public DiaryScoreVO findById(Long id);

}
