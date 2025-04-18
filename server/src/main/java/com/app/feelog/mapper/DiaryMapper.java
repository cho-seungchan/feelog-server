package com.app.feelog.mapper;

import com.app.feelog.domain.vo.DiaryVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DiaryMapper {

    // 다이어리 등록
    public void insert(DiaryVO diaryVO);

}
