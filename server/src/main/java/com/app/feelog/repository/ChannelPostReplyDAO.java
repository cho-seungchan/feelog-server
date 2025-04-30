package com.app.feelog.repository;

import com.app.feelog.domain.dto.ChannelPostReplyDTO;
import com.app.feelog.domain.vo.ChannelPostReplyVO;
import com.app.feelog.mapper.ChannelPostReplyMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<ChannelPostReplyDTO> findReplyByPostId(Long id){
        return channelPostReplyMapper.selectReplyByPostId(id);
    };

    public List<Long> findIsLiked(Long memberId){
        return channelPostReplyMapper.selectIsLiked(memberId);
    };


}
