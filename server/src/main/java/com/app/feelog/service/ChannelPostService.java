package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostDTO;
import com.app.feelog.domain.dto.ChannelPostFileDTO;
import com.app.feelog.domain.dto.PostJkDTO;
import com.app.feelog.domain.vo.ChannelPostFileVO;
import com.app.feelog.domain.vo.ChannelPostVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface ChannelPostService {

    void writeChannelPost(ChannelPostDTO dto);

    ChannelPostDTO getChannelPost(Long id);

    void updateChannelPost(ChannelPostDTO dto);

    default ChannelPostDTO toDTO(ChannelPostVO vo) {
        ChannelPostDTO dto = new ChannelPostDTO();
        dto.setId(vo.getId());
        dto.setPostType(vo.getPostType());
        dto.setPostFilePath(vo.getPostFilePath());
        dto.setPostFileName(vo.getPostFileName());
        dto.setPostFileSize(vo.getPostFileSize());
        dto.setMemberId(vo.getMemberId());
        dto.setChannelId(vo.getChannelId());
        dto.setPostStatus(vo.getPostStatus());
        dto.setCreatedDate(vo.getCreatedDate());
        dto.setUpdatedDate(vo.getUpdatedDate());
        return dto;
    }

    default PostJkDTO toPostJkDTO(ChannelPostDTO dto) {
        PostJkDTO postDto = new PostJkDTO();
        postDto.setId(dto.getId());
        postDto.setPostTitle(dto.toVO().getPostTitle());
        postDto.setPostContent(dto.toVO().getPostContent());
        postDto.setPostStatus(dto.getPostStatus());
        postDto.setCreatedDate(dto.getCreatedDate());
        postDto.setUpdatedDate(dto.getUpdatedDate());
        return postDto;
    }

    default List<ChannelPostFileDTO> toFileDTOList(List<ChannelPostFileVO> voList) {
        List<ChannelPostFileDTO> dtoList = new ArrayList<>();
        for (ChannelPostFileVO vo : voList) {
            ChannelPostFileDTO dto = new ChannelPostFileDTO();
            dto.setId(vo.getId());
            dto.setPostId(vo.getPostId());
            dto.setCreatedDate(vo.getCreatedDate());
            dto.setUpdatedDate(vo.getUpdatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }
}