package com.app.feelog.repository;

import com.app.feelog.domain.vo.ChannelPostReplyVO;
import com.app.feelog.mapper.ChannelPostReplyMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChannelPostReplyDAO {
    private final ChannelPostReplyMapper channelPostReplyMapper;

    public void saveReply(ChannelPostReplyVO channelPostReplyVO){
        channelPostReplyMapper.insertReply(channelPostReplyVO);
    };

    public void savePostReply(ChannelPostReplyVO channelPostReplyVO){
        channelPostReplyMapper.insertPostReply(channelPostReplyVO);
    };
}
