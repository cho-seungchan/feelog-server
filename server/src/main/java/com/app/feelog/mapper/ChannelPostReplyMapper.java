package com.app.feelog.mapper;

import com.app.feelog.domain.dto.ChannelPostReplyDTO;
import com.app.feelog.domain.vo.ChannelPostReplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChannelPostReplyMapper {
    public void insertReply(ChannelPostReplyVO channelPostReplyVO);

    public void insertPostReply(ChannelPostReplyVO channelPostReplyVO);

    public List<ChannelPostReplyDTO> selectReplyByPostId(Long id);

    public List<Long> selectIsLiked(Long memberId);
}
