package com.app.feelog.service;

import com.app.feelog.domain.dto.ChallengeDiaryDTO;
import com.app.feelog.domain.dto.ChannelPostDTO;
import com.app.feelog.domain.dto.PostJkDTO;
import com.app.feelog.domain.vo.ChallengeDiaryVO;

public interface ChallengeDiaryService {

    void addChallengeDiary(ChallengeDiaryDTO challengeDiaryDTO);

    ChallengeDiaryDTO findById(Long id);

    default PostJkDTO toPostJkDTO(ChannelPostDTO dto) {
        PostJkDTO postDto = new PostJkDTO();
        postDto.setPostTitle(dto.toVO().getPostTitle());
        postDto.setPostContent(dto.toVO().getPostContent());
        postDto.setPostStatus(dto.getPostStatus());
        return postDto;
    }

}
