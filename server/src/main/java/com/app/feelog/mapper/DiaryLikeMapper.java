package com.app.feelog.mapper;

import com.app.feelog.domain.vo.DiaryLikeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryLikeMapper {
    public int selectLikeCount(Long diaryId);

    public int selectReplyCount(Long diaryId);

    public void insertSuperLike(DiaryLikeVO diaryLikeVO);

    public void insertDiaryLike(DiaryLikeVO diaryLikeVO);

    public void deleteDiaryLike(Long diaryId);

    public List<Long> selectDiaryLikeIdsByMemberId(Long memberId);
}
