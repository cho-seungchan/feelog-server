package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryLikeDTO;
import com.app.feelog.domain.dto.DiaryReplyLikeDTO;
import com.app.feelog.domain.vo.DiaryLikeVO;
import com.app.feelog.domain.vo.DiaryReplyLikeVO;
import com.app.feelog.repository.DiaryLikeDAO;
import com.app.feelog.repository.DiaryReplyLikeDAO;
import com.app.feelog.service.voToDto.LikeService;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class LikeServiceImpl implements LikeService {
    private final DiaryReplyLikeDAO diaryReplyLikeDAO;
    private final DiaryLikeDAO diaryLikeDAO;

    @Override
    public void insertDiaryReplyLike(DiaryReplyLikeDTO diaryReplyLikeDTO) {
        DiaryReplyLikeVO diaryReplyLikeVO = diaryReplyLikeDTO.toVO();
        log.info(diaryReplyLikeVO.toString());
        DiaryReplyLikeVO checkReply = diaryReplyLikeDAO.findDiaryReplyByMemberIdAndReplyId(diaryReplyLikeVO.getMemberId(), diaryReplyLikeVO.getReplyId());

        if(checkReply == null){
            diaryReplyLikeDAO.saveSuperLike(diaryReplyLikeVO);
            diaryReplyLikeDAO.saveDiaryReplyLike(diaryReplyLikeVO);
        } else {
            diaryReplyLikeDAO.deleteDiaryReplyLikeByReplyId(diaryReplyLikeVO.getMemberId() ,diaryReplyLikeVO.getReplyId());
        }
    }

    @Override
    public List<Long> getLikeIdsByMemberId(Long memberId) {
        return diaryReplyLikeDAO.findLikeIdsByMemberId(memberId);
    }

    @Override
    public int getDiaryReplyLikeCount(Long replyId) {
        return diaryReplyLikeDAO.findDiaryReplyLikeCount(replyId);
    }

    @Override
    public void addOrDeleteDiaryLike(DiaryLikeDTO diaryLikeDTO) {
        DiaryLikeVO diaryLikeVO = diaryLikeDTO.toVO();
        List<Long> likeIds = diaryLikeDAO.selectDiaryLikeIdsByMemberId(diaryLikeVO.getMemberId());

        boolean result = likeIds.stream().anyMatch(id->id.equals(diaryLikeVO.getDiaryId()));

        if (result) {
            diaryLikeDAO.deleteDiaryLike(diaryLikeVO.getDiaryId());
        }else{
            diaryLikeDAO.insertSuperLike(diaryLikeVO);
            diaryLikeDAO.insertDiaryLike(diaryLikeVO);
        }
    }

    @Override
    public List<Long> getDiaryLikeIdsByMemberId(Long memberId) {
        return diaryLikeDAO.selectDiaryLikeIdsByMemberId(memberId);
    }
}
