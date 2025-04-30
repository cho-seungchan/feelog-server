package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryScoreDTO;
import com.app.feelog.domain.vo.DiaryScoreVO;

import java.util.Optional;

public interface DiaryScoreService {

    Optional<DiaryScoreDTO> getScoreById(Long id);

    int getEmotionScore(String contents);

    default DiaryScoreDTO toDTO(DiaryScoreVO vo) {
        DiaryScoreDTO dto = new DiaryScoreDTO();
        dto.setId(vo.getId());
        dto.setScoreLevelName(vo.getScoreLevelName());
        dto.setScoreMessage(vo.getScoreMessage());
        dto.setScoreFilePath(vo.getScoreFilePath());
        dto.setScoreFileName(vo.getScoreFileName());
        dto.setCreatedDate(vo.getCreatedDate());
        dto.setUpdatedDate(vo.getUpdatedDate());
        return dto;
    }
}
