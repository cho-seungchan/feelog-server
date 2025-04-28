package com.app.feelog.repository;

import com.app.feelog.domain.vo.CommunityPostNotificationVO;
import com.app.feelog.mapper.CommunityPostNotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommunityPostNotificationDAO {

    private final CommunityPostNotificationMapper communityPostNotificationMapper;

    public void insert(CommunityPostNotificationVO communityPostNotificationVO) {
        communityPostNotificationMapper.insert(communityPostNotificationVO);
    }

}
