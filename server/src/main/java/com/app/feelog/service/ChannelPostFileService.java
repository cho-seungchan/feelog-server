package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostFileDTO;
import com.app.feelog.domain.vo.ChannelPostFileVO;

import java.util.List;

public interface ChannelPostFileService {

    void addChannelPostFile(ChannelPostFileDTO dto);

    void removeAllFilesByPostId(Long postId);

    List<ChannelPostFileDTO> getFilesByPostId(Long postId);

    default ChannelPostFileDTO toDTO(ChannelPostFileVO vo) {
        ChannelPostFileDTO dto = new ChannelPostFileDTO();
        dto.setId(vo.getId());
        dto.setPostId(vo.getPostId());
        dto.setFilePath(vo.getFilePath());
        dto.setFileName(vo.getFileName());
        dto.setFileSize(vo.getFileSize());
        return dto;
    }
}
