package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.DiaryReplyDTO;
import com.app.feelog.domain.vo.DiaryReplyVO;

import java.util.List;

public interface ReplyService {
    public void addDiaryReply(DiaryReplyDTO diaryReplyDTO);

    public List<DiaryReplyDTO> getDiaryReplyByDiaryId(Long diaryId);

    public default DiaryReplyDTO toDTO(DiaryReplyVO diaryReplyVO){
        DiaryReplyDTO diaryReplyDTO = new DiaryReplyDTO();
        if(diaryReplyVO.getDiaryId() != null){
            diaryReplyDTO.setDiaryId(diaryReplyVO.getDiaryId());
            diaryReplyDTO.setId(diaryReplyVO.getDiaryId());
            diaryReplyDTO.setMemberId(diaryReplyVO.getMemberId());
            diaryReplyDTO.setReplyFilePath(diaryReplyVO.getReplyFilePath());
            diaryReplyDTO.setReplyFileName(diaryReplyVO.getReplyFileName());
            diaryReplyDTO.setUpdatedDate(diaryReplyVO.getUpdatedDate());
            diaryReplyDTO.setCreatedDate(diaryReplyVO.getCreatedDate());
            diaryReplyDTO.setReplyContent(diaryReplyVO.getReplyContent());
            diaryReplyDTO.setReplyStatus(diaryReplyVO.getReplyStatus());
        }

        return diaryReplyDTO;
    }
}
