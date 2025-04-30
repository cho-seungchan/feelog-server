package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryDTO;
import com.app.feelog.domain.dto.DiarySearchDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryPaginationDTO;
import com.app.feelog.domain.vo.DiaryVO;
import com.app.feelog.util.pagination.PostPagination;

import java.util.List;

public interface DiaryService {
    Long writeDiary(DiaryDTO diaryDTO);

    DiaryDTO getDiary(Long id);

    void updateDiary(DiaryDTO diaryDTO);

    List<DiarySearchDTO> searchDiaries(String keyword);

    List<DiarySearchDTO> getRecentDiaries();

    List<DiarySearchDTO> searchMoreDiaries(String keyword, int limit, int offset);

//    박정근 :: 다이어리 페이지네이션
    public DiaryPaginationDTO getDiaryList(PostPagination postPagination);

    public DiaryPaginationDTO getDiaryListAllAndSubscribe(PostPagination postPagination);

    public DiaryPaginationDTO getDiaryListAll(PostPagination postPagination);

    public List<Long> getDiaryReportIds(Long memberId);



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
        dto.setDiaryStatus(vo.getDiaryStatus());
        dto.setCreatedDate(vo.getCreatedDate());
        dto.setUpdatedDate(vo.getUpdatedDate());
        return dto;
    }
}
