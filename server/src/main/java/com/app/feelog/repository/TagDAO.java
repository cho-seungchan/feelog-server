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

    public void deleteTagById(Long tagId) {
        tagMapper.deleteTagById(tagId);
    }

    public void softDeleteTagByContentAndPostId( Long channelPostId, String content) {
        tagMapper.softDeleteTagByContentAndPostId( channelPostId, content);
    }

    public Long findTagIdByContentAndChannelPostId(String content, Long channelPostId) {
        return tagMapper.findTagIdByContentAndChannelPostId(content, channelPostId);
    }

    public Long findTagIdByContent(String content) {
        return tagMapper.findTagIdByContent(content);
    }

    public void softDeleteById(Long id) {
        tagMapper.softDeleteById(id);
    }
}