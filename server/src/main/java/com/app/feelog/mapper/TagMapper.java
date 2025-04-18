package com.app.feelog.mapper;

import com.app.feelog.domain.vo.DiaryTagVO;
import com.app.feelog.domain.vo.TagVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    // 태그 저장
    void insertTag(TagVO tagVO);

    // 다이어리-태그 연결 저장
    void insertDiaryTag(DiaryTagVO diaryTagVO);

    // contents로 태그 목록 조회 (동일한 내용으로 여러 명 가능하므로 리스트)
    List<TagVO> findTagsByContents(String contents);

}
