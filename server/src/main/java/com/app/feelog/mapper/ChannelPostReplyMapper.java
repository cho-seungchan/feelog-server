package com.app.feelog.mapper;

import com.app.feelog.domain.vo.ChannelPostReplyVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChannelPostReplyMapper {
    public void insertReply(ChannelPostReplyVO channelPostReplyVO);

    public void insertPostReply(ChannelPostReplyVO channelPostReplyVO);
}
