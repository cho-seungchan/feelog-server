package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.DiaryLikeDTO;
import com.app.feelog.domain.dto.DiaryReplyLikeDTO;

import java.util.List;

public interface LikeService {
    public void insertDiaryReplyLike(DiaryReplyLikeDTO diaryReplyLikeDTO);

    public List<Long> getLikeIdsByMemberId(Long memberId);

    public int getDiaryReplyLikeCount(Long replyId);

    public void addOrDeleteDiaryLike(DiaryLikeDTO diaryLikeDTO);

    public List<Long> getDiaryLikeIdsByMemberId(Long memberId);
}
