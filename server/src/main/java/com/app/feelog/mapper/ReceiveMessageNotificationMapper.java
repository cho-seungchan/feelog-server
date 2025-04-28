package com.app.feelog.mapper;

import com.app.feelog.domain.vo.ReceiveMessageNotificationVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceiveMessageNotificationMapper {

    void insert(ReceiveMessageNotificationVO receiveMessageNotificationVO);

}
