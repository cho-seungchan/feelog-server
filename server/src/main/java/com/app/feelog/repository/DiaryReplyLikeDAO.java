package com.app.feelog.repository;

import com.app.feelog.domain.vo.DiaryReplyLikeVO;
import com.app.feelog.mapper.DiaryReplyLikeMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiaryReplyLikeDAO {
    private final DiaryReplyLikeMapper diaryReplyLikeMapper;

    public void saveSuperLike(DiaryReplyLikeVO diaryReplyLikeVO){
        diaryReplyLikeMapper.insertSuperLike(diaryReplyLikeVO);
    };

    public void saveDiaryReplyLike(DiaryReplyLikeVO diaryReplyLikeVO){
        diaryReplyLikeMapper.insertDiaryReplyLike(diaryReplyLikeVO);
    };

    public List<Long> findLikeIdsByMemberId(Long memberId){
        return diaryReplyLikeMapper.selectLikeIdsByMemberId(memberId);
    };

    public int findDiaryReplyLikeCount(Long replyId){
        return diaryReplyLikeMapper.selectDiaryReplyLikeCount(replyId);
    };

    public DiaryReplyLikeVO findDiaryReplyByMemberIdAndReplyId(@Param("memberId") Long memberId, @Param("replyId") Long replyId){
        return diaryReplyLikeMapper.selectDiaryReplyByMemberIdAndReplyId(memberId, replyId);
    };

    public void deleteDiaryReplyLikeByReplyId(@Param("memberId") Long memberId, @Param("replyId") Long replyId){
        diaryReplyLikeMapper.deleteDiaryReplyLikeByReplyId(memberId, replyId);
    };


}
