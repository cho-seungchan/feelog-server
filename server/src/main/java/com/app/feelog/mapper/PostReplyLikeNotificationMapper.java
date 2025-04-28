package com.app.feelog.mapper;

import com.app.feelog.domain.vo.PostReplyLikeNotificationVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostReplyLikeNotificationMapper {

    void insert(PostReplyLikeNotificationVO postReplyLikeNotificationVO);

}
