package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryDTO;
import com.app.feelog.domain.vo.DiaryVO;

public interface DiaryService {
    Long writeDiary(DiaryDTO diaryDTO);

    DiaryDTO getDiary(Long id);

    void updateDiary(DiaryDTO diaryDTO);


    default DiaryDTO toDTO(DiaryVO vo) {
        DiaryDTO dto = new DiaryDTO();
        dto.setId(vo.getId());
        dto.setDiaryTitle(vo.getDiaryTitle());
        dto.setDiaryContent(vo.getDiaryContent());
        dto.setDiaryOpen(vo.getDiaryOpen());
        dto.setDiaryNameOpen(vo.getDiaryNameOpen());
        dto.setDiaryFilePath(vo.getDiaryFilePath());
        dto.setDiaryFileName(vo.getDiaryFileName());
        dto.setDiaryFileSize(vo.getDiaryFileSize());
        dto.setDiaryWeather(vo.getDiaryWeather());
        dto.setMemberId(vo.getMemberId());
        dto.setFeelId(vo.getFeelId());
        dto.setDiaryStatus(vo.getDiaryStatus());
        dto.setCreatedDate(vo.getCreatedDate());
        dto.setUpdatedDate(vo.getUpdatedDate());
        return dto;
    }
}
