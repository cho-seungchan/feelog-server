package com.app.feelog.repository;

import com.app.feelog.mapper.DiaryLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DiaryLikeDAO {
    private final DiaryLikeMapper diaryLikeMapper;

    public int findLikeCount(Long diaryId){
        return diaryLikeMapper.selectLikeCount(diaryId);
    };

    public int findReplyCount(Long diaryId){
        return diaryLikeMapper.selectReplyCount(diaryId);
    };
}
