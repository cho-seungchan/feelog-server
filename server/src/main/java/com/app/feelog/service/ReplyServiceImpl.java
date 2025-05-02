package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryReplyDTO;
import com.app.feelog.domain.vo.DiaryReplyVO;
import com.app.feelog.repository.DiaryReplyDAO;
import com.app.feelog.service.voToDto.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class ReplyServiceImpl implements ReplyService {
    private final DiaryReplyDAO diaryReplyDAO;

    @Override
    public void addDiaryReply(DiaryReplyDTO diaryReplyDTO) {
        DiaryReplyVO diaryReplyVO = diaryReplyDTO.toVO();
        diaryReplyDAO.saveSuperReply(diaryReplyVO);
        diaryReplyDAO.saveDiaryReply(diaryReplyVO);
    }

    @Override
    public List<DiaryReplyDTO> getDiaryReplyByDiaryId(Long diaryId) {
        List<DiaryReplyDTO> replyList = diaryReplyDAO.findDiaryReplyByDiaryId(diaryId);
       return replyList;
    }
}
