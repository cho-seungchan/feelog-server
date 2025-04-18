package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryFileDTO;
import com.app.feelog.domain.dto.FileDTO;
import com.app.feelog.domain.vo.FileVO;
import com.app.feelog.repository.DiaryFileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryFileService {
    private final DiaryFileDAO diaryFileDAO;

    // 첨부 파일 등록
    public void addDiaryFile(DiaryFileDTO diaryFileDTO) {
        diaryFileDAO.save(diaryFileDTO.toVO());
    }

    // 다이어리의 첨부 파일 전체 삭제
    public void removeAllFilesByDiaryId(Long diaryId) {
        diaryFileDAO.deleteByDiaryId(diaryId);
    }

    // 첨부 파일 목록 조회 → FileDTO로 변환
    public List<FileDTO> getFilesByDiaryId(Long diaryId) {
        List<FileVO> fileVOList = diaryFileDAO.findFilesByDiaryId(diaryId);

        List<FileDTO> fileDTOList = new ArrayList<>();
        for (FileVO fileVO : fileVOList) {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setId(fileVO.getId());
            fileDTO.setFilePath(fileVO.getFilePath());
            fileDTO.setFileName(fileVO.getFileName());
            fileDTO.setFileSize(fileVO.getFileSize());
            fileDTO.setFileStatus(fileVO.getFileStatus());
            fileDTO.setCreatedDate(fileVO.getCreatedDate());
            fileDTO.setUpdatedDate(fileVO.getUpdatedDate());
            fileDTOList.add(fileDTO);
        }

        return fileDTOList;
    }
}
