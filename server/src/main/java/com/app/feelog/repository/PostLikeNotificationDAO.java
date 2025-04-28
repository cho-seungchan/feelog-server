package com.app.feelog.repository;

import com.app.feelog.domain.vo.PostLikeNotificationVO;
import com.app.feelog.mapper.PostLikeNotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostLikeNotificationDAO {

    private final PostLikeNotificationMapper postLikeNotificationMapper;

    public void insert(PostLikeNotificationVO postLikeNotificationVO) {
        postLikeNotificationMapper.insert(postLikeNotificationVO);
    }

}
