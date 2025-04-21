package com.app.feelog.repository;

import com.app.feelog.domain.vo.DiaryTagVO;
import com.app.feelog.domain.vo.TagVO;
import com.app.feelog.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagDAO {

    private final TagMapper tagMapper;

    // 태그 저장
    public void saveTag(TagVO tagVO) {
        tagMapper.insertTag(tagVO);
    }

    // 다이어리-태그 연결 저장
    public void saveDiaryTag(DiaryTagVO diaryTagVO) {
        tagMapper.insertDiaryTag(diaryTagVO);
    }

    // 내용으로 태그 찾기 (중복 체크 or 조회용)
    public List<TagVO> findTagsByContents(String contents) {
        return tagMapper.findTagsByContents(contents);
    }

    public void deactivateTag(Long tagId) {
        tagMapper.deactivateTag(tagId);
    }
}