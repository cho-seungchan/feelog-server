package com.app.feelog.repository;

import com.app.feelog.domain.vo.PostReplyLikeNotificationVO;
import com.app.feelog.mapper.PostReplyLikeNotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostReplyLikeNotificationDAO {

    private final PostReplyLikeNotificationMapper postReplyLikeNotificationMapper;

    public void insert(PostReplyLikeNotificationVO postReplyLikeNotificationVO) {
        postReplyLikeNotificationMapper.insert(postReplyLikeNotificationVO);
    }

}
