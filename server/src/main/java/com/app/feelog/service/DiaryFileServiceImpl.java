package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryFileDTO;
import com.app.feelog.domain.dto.FileDTO;
import com.app.feelog.domain.vo.FileVO;
import com.app.feelog.repository.DiaryFileDAO;
import com.app.feelog.repository.FileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryFileServiceImpl implements DiaryFileService {
    private final DiaryFileDAO diaryFileDAO;
    private final FileDAO fileDAO;

    @Override
    public void addDiaryFile(DiaryFileDTO diaryFileDTO) {
        diaryFileDAO.save(diaryFileDTO.toVO());
    }

    @Override
    public void removeAllFilesByDiaryId(Long diaryId) {
        diaryFileDAO.deleteByDiaryId(diaryId);
    }

    @Override
    public List<FileDTO> getFilesByDiaryId(Long diaryId) {
        List<com.app.feelog.domain.vo.FileVO> fileVOList = diaryFileDAO.findFilesByDiaryId(diaryId);
        List<FileDTO> fileDTOList = new ArrayList<>();
        for (com.app.feelog.domain.vo.FileVO fileVO : fileVOList) {
            fileDTOList.add(toFileDTO(fileVO));
        }
        return fileDTOList;
    }

    @Override
    public void deactivateFile(Long fileId) {
        fileDAO.deactivateFile(fileId);
    }
}