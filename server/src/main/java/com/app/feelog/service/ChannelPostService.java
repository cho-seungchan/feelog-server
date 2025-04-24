package com.app.feelog.service;

import com.app.feelog.domain.dto.*;
import com.app.feelog.domain.vo.ChannelPostFileVO;
import com.app.feelog.domain.vo.ChannelPostVO;
import com.app.feelog.util.pagination.PostPagination;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface ChannelPostService {

    void writeChannelPost(ChannelPostDTO dto);

    ChannelPostDTO getChannelPost(Long id);

    void updateChannelPost(ChannelPostDTO dto);

    void deleteTags(List<Long> tagIds);

    default ChannelPostDTO toDTO(ChannelPostVO vo) {
        ChannelPostDTO dto = new ChannelPostDTO();
        dto.setId(vo.getId());
        dto.setPostType(vo.getPostType());
        dto.setPostFilePath(vo.getPostFilePath());
        dto.setPostFileName(vo.getPostFileName());
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

    List<ChannelPostSearchDTO> getRecentChannelPosts();

//    박정근 :: 전체조회
    ChannelPostListDTO getPostAll(PostPagination pagination);

    List<ChannelPostSearchDTO> searchChannelPosts(String keyword);


}