package com.app.feelog.repository;

import com.app.feelog.domain.dto.DiaryReplyDTO;
import com.app.feelog.domain.vo.DiaryReplyVO;
import com.app.feelog.mapper.DiaryReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiaryReplyDAO {
    private final DiaryReplyMapper diaryReplyMapper;

    public void saveSuperReply(DiaryReplyVO diaryReply){
        diaryReplyMapper.insertSuperReply(diaryReply);
    };

    public void saveDiaryReply(DiaryReplyVO diaryReply){
        diaryReplyMapper.insertDiaryReply(diaryReply);
    };

    public List<DiaryReplyDTO> findDiaryReplyByDiaryId(Long diaryId){
        return diaryReplyMapper.selectDiaryReplyByDiaryId(diaryId);
    };

    public int findDiaryReplyCount(Long diaryId){
        return diaryReplyMapper.selectDiaryReplyCount(diaryId);
    };


}
