package com.app.feelog.repository;

import com.app.feelog.domain.dto.PostReplyNotificationDTO;
import com.app.feelog.domain.vo.PostReplyNotificationVO;
import com.app.feelog.mapper.PostReplyNotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostReplyNotificationDAO {

    private final PostReplyNotificationMapper postReplyNotificationMapper;

    public void insert(PostReplyNotificationVO postReplyNotificationVO) {
        postReplyNotificationMapper.insert(postReplyNotificationVO);
    }

}
