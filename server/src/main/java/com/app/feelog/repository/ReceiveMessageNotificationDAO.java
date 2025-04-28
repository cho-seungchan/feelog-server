package com.app.feelog.repository;

import com.app.feelog.domain.vo.ReceiveMessageNotificationVO;
import com.app.feelog.mapper.ReceiveMessageNotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReceiveMessageNotificationDAO {

    private final ReceiveMessageNotificationMapper receiveMessageNotificationMapper;

    public void insert(ReceiveMessageNotificationVO receiveMessageNotificationVO) {
        receiveMessageNotificationMapper.insert(receiveMessageNotificationVO);
    }

}
