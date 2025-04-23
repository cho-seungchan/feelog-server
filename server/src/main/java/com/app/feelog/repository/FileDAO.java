package com.app.feelog.repository;

import com.app.feelog.domain.vo.ChannelPostFileVO;
import com.app.feelog.domain.vo.FileVO;
import com.app.feelog.mapper.ChannelPostFileMapper;
import com.app.feelog.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;
    private final ChannelPostFileMapper channelPostFileMapper;

    public void save(FileVO fileVO) {
        fileMapper.insert(fileVO);
    }

    public Long saved(ChannelPostFileVO vo) {
        channelPostFileMapper.insertChannelPostFile(vo);
        return vo.getId();
    }


    public void deactivateFile(Long fileId) {
        fileMapper.deactivateFile(fileId);
    }

    public void softDeleteFile(Long fileId) {
        fileMapper.softDeleteFile(fileId);
    }

    public void softDeleteByFileName(String fileName) {
        fileMapper.softDeleteByFileName(fileName);
    }

}
