package com.app.feelog.mapper;

import com.app.feelog.domain.vo.PostReplyNotificationVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostReplyNotificationMapper {

    void insert(PostReplyNotificationVO postReplyNotificationVO);

}
