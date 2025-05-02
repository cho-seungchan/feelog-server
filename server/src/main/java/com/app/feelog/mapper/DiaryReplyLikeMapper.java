package com.app.feelog.mapper;

import com.app.feelog.domain.vo.DiaryReplyLikeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiaryReplyLikeMapper {
    public void insertSuperLike(DiaryReplyLikeVO diaryReplyLikeVO);

    public void insertDiaryReplyLike(DiaryReplyLikeVO diaryReplyLikeVO);

    public List<Long> selectLikeIdsByMemberId(Long memberId);

    public int selectDiaryReplyLikeCount(Long replyId);

    public DiaryReplyLikeVO selectDiaryReplyByMemberIdAndReplyId(@Param("memberId") Long memberId, @Param("replyId") Long replyId);

    public void deleteDiaryReplyLikeByReplyId(@Param("memberId") Long memberId, @Param("replyId") Long replyId);
}
