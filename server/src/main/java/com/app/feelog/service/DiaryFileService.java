package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryFileDTO;
import com.app.feelog.domain.dto.FileDTO;
import com.app.feelog.domain.vo.FileVO;

import java.util.List;

public interface DiaryFileService {
    void addDiaryFile(DiaryFileDTO diaryFileDTO);

    void removeAllFilesByDiaryId(Long diaryId);

    List<FileDTO> getFilesByDiaryId(Long diaryId);

    void deactivateFile(Long fileId);

    default FileDTO toFileDTO(FileVO fileVO) {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setId(fileVO.getId());
        fileDTO.setFilePath(fileVO.getFilePath());
        fileDTO.setFileName(fileVO.getFileName());
        fileDTO.setFileSize(fileVO.getFileSize());
        fileDTO.setFileStatus(fileVO.getFileStatus());
        fileDTO.setCreatedDate(fileVO.getCreatedDate());
        fileDTO.setUpdatedDate(fileVO.getUpdatedDate());
        return fileDTO;
    }
}