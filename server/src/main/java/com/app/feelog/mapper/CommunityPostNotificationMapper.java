package com.app.feelog.mapper;

import com.app.feelog.domain.vo.CommunityPostNotificationVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityPostNotificationMapper {

    void insert(CommunityPostNotificationVO communityPostNotificationVO);

}
