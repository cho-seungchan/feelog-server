package com.app.feelog.repository;

import com.app.feelog.domain.vo.FileVO;
import com.app.feelog.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

    public void save(FileVO fileVO) {
        fileMapper.insert(fileVO);
    }

    public void deactivateFile(Long fileId) {
        fileMapper.deactivateFile(fileId);
    }

}
