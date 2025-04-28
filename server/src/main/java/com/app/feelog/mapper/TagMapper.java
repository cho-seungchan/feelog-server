package com.app.feelog.mapper;

import com.app.feelog.domain.vo.DiaryTagVO;
import com.app.feelog.domain.vo.TagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {
    // 태그 저장
    void insertTag(TagVO tagVO);

    // 다이어리-태그 연결 저장
    void insertDiaryTag(DiaryTagVO diaryTagVO);

    // contents로 태그 목록 조회
    List<TagVO> findTagsByContents(String contents);

    void deactivateTag(Long tagId);

    void deleteTagById(Long tagId);

    void softDeleteTagByContentAndPostId(@Param("channelPostId") Long channelPostId,
                                         @Param("content") String content);

    Long findTagIdByContentAndChannelPostId(@Param("content") String content,
                                            @Param("channelPostId") Long channelPostId);

    Long findTagIdByContent(@Param("content") String content);

    void softDeleteById(@Param("id") Long id);

    List<String> findPopularTags();

    TagVO findByContent(@Param("content") String content);

}
