package com.app.feelog.mapper;

import com.app.feelog.domain.vo.PostJKVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface PostMapper {

    // 1. 등록
    void insertPost(PostJKVO postVO);

    // 2. 단건 조회
    PostJKVO selectPostById(Long id);

    // 3. 수정
    void updatePost(PostJKVO postVO);

}
