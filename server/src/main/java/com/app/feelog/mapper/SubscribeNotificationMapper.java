package com.app.feelog.mapper;

import com.app.feelog.domain.vo.SubscribeNotificationVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubscribeNotificationMapper {

    void insert(SubscribeNotificationVO subscribeNotificationVO);

}
