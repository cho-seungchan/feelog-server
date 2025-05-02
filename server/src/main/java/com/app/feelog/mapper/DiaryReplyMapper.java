package com.app.feelog.mapper;

import com.app.feelog.domain.dto.DiaryReplyDTO;
import com.app.feelog.domain.vo.DiaryReplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryReplyMapper {
    public void insertSuperReply(DiaryReplyVO diaryReply);

    public void insertDiaryReply(DiaryReplyVO diaryReply);

    public List<DiaryReplyDTO> selectDiaryReplyByDiaryId(Long diaryId);

    public int selectDiaryReplyCount(Long diaryId);
}
