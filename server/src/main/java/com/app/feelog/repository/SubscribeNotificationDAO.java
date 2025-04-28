package com.app.feelog.repository;

import com.app.feelog.domain.vo.SubscribeNotificationVO;
import com.app.feelog.mapper.SubscribeNotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SubscribeNotificationDAO {

    private final SubscribeNotificationMapper subscribeNotificationMapper;

    public void insert(SubscribeNotificationVO subscribeNotificationVO) {
        subscribeNotificationMapper.insert(subscribeNotificationVO);
    }

}
