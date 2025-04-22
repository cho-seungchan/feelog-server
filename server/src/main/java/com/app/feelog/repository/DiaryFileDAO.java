package com.app.feelog.repository;

import com.app.feelog.domain.vo.DiaryFileVO;
import com.app.feelog.domain.vo.FileVO;
import com.app.feelog.mapper.DiaryFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiaryFileDAO {
    private final DiaryFileMapper diaryFileMapper;

    // 첨부 파일 등록
    public void save(DiaryFileVO diaryFileVO) {
        diaryFileMapper.insert(diaryFileVO);
    }

    // 다이어리에 연결된 파일 삭제
    public void deleteByDiaryId(Long diaryId) {
        diaryFileMapper.deleteByDiaryId(diaryId);
    }

    // 다이어리에 연결된 파일 목록 조회
    public List<FileVO> findFilesByDiaryId(Long diaryId) {
        return diaryFileMapper.selectFilesByDiaryId(diaryId);
    }

    public boolean existsById(Long id) {
        return diaryFileMapper.existsById(id);
    }
}