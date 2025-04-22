package com.app.feelog.repository;

import com.app.feelog.domain.vo.DiaryVO;
import com.app.feelog.domain.vo.PostJKVO;
import com.app.feelog.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDAO {

    private final PostMapper postMapper;

    public void insertPost(PostJKVO postVO) {
        postMapper.insertPost(postVO);
    }

    public PostJKVO findById(Long id) {
        return postMapper.selectPostById(id);
    }

    public void updatePost(PostJKVO postVO) {
        postMapper.updatePost(postVO);
    }

}
