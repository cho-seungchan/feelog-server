package com.app.feelog.repository;

import com.app.feelog.domain.vo.DiaryLikeVO;
import com.app.feelog.mapper.DiaryLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public void insertSuperLike(DiaryLikeVO diaryLikeVO){
        diaryLikeMapper.insertSuperLike(diaryLikeVO);
    };

    public void insertDiaryLike(DiaryLikeVO diaryLikeVO){
        diaryLikeMapper.insertDiaryLike(diaryLikeVO);
    };

    public void deleteDiaryLike(Long diaryId){
        diaryLikeMapper.deleteDiaryLike(diaryId);
    };

    public List<Long> selectDiaryLikeIdsByMemberId(Long memberId){
        return diaryLikeMapper.selectDiaryLikeIdsByMemberId(memberId);
    };
}
