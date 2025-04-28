package com.app.feelog.mapper;

import com.app.feelog.domain.vo.PostLikeNotificationVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostLikeNotificationMapper {

    void insert(PostLikeNotificationVO postLikeNotificationVO);

}
