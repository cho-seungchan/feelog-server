package com.app.feelog.mapper;

import com.app.feelog.domain.dto.DiarySearchDTO;
import com.app.feelog.domain.vo.DiaryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;


@Mapper
public interface DiaryMapper {

    // 다이어리 등록
    public void insert(DiaryVO diaryVO);

    // 단일 조회 (수정용)
    Optional<DiaryVO> selectById(Long id);

    // 수정
    void update(DiaryVO diaryVO);

    List<DiarySearchDTO> searchDiaries(String keyword);

    List<DiarySearchDTO> getRecentDiaries();

}
