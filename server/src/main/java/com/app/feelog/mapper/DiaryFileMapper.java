package com.app.feelog.mapper;

import com.app.feelog.domain.vo.DiaryFileVO;
import com.app.feelog.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryFileMapper {

    // 첨부 이미지 등록
    void insert(DiaryFileVO diaryFileVO);

    // 첨부 이미지 전체 삭제
    void deleteByDiaryId(Long diaryId);

    // 첨부 이미지 조회
    List<FileVO> selectFilesByDiaryId(Long diaryId);

    boolean existsById(Long id);
}
