package com.app.feelog.mapper;

import com.app.feelog.domain.vo.DiaryTagVO;
import com.app.feelog.domain.vo.TagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DiaryTagMapper {
    public Optional<TagVO> findByContent(String content);

    public void insertTag(TagVO tagVO);

    public void insertDiaryTag(DiaryTagVO diaryTagVO);

    public void deleteByDiaryId(Long diaryId);

    void save(@Param("diaryId") Long diaryId, @Param("tagId") Long tagId);

    List<String> selectTagContentsByDiaryId(Long diaryId);

}
