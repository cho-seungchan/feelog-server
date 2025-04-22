package com.app.feelog.service;

import com.app.feelog.domain.dto.PostDTO;
import com.app.feelog.domain.dto.PostJkDTO;
import com.app.feelog.domain.enumeration.PostStatus;
import com.app.feelog.domain.vo.PostJKVO;
import com.app.feelog.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class PostJkServiceImpl implements PostJkService {

    private final PostDAO postDAO;
    private final PostDTO postDTO;

    @Override
    public void writePost(PostJkDTO dto) {
        PostJKVO postVO = dto.toVO();
        postDTO.setPostStatus(PostStatus.ACTIVE); // 등록 시 기본값
        postDAO.insertPost(postVO);
        dto.setId(postVO.getId()); // 반환된 ID 세팅
    }

    @Override
    public PostJkDTO getPost(Long id) {
        return toDTO(postDAO.findById(id));
    }

    @Override
    public void updatePost(PostJkDTO dto) {
        postDAO.updatePost(dto.toVO());
    }
}

