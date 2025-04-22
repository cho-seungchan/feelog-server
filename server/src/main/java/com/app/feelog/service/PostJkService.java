package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostDTO;
import com.app.feelog.domain.dto.PostJkDTO;
import com.app.feelog.domain.vo.PostJKVO;

public interface PostJkService {

    void writePost(PostJkDTO dto);

    PostJkDTO getPost(Long id);

    void updatePost(PostJkDTO dto);

    default PostJkDTO toDTO(PostJKVO vo) {
        PostJkDTO dto = new PostJkDTO();
        dto.setId(vo.getId());
        dto.setPostTitle(vo.getPostTitle());
        dto.setPostContent(vo.getPostContent());
        dto.setPostStatus(vo.getPostStatus());
        dto.setCreatedDate(vo.getCreatedDate());
        dto.setUpdatedDate(vo.getUpdatedDate());
        return dto;
    }
}
