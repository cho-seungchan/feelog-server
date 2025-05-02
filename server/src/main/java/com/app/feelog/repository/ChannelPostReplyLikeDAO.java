package com.app.feelog.repository;

import com.app.feelog.domain.vo.ChannelPostReplyLikeVO;
import com.app.feelog.domain.vo.ChannelPostReplyVO;
import com.app.feelog.mapper.ChannelPostReplyLikeMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChannelPostReplyLikeDAO {
    private final ChannelPostReplyLikeMapper channelPostReplyLikeMapper;

    public void saveReplyLike(ChannelPostReplyLikeVO channelPostReplyLikeVO){
        channelPostReplyLikeMapper.insertReplyLike(channelPostReplyLikeVO);
    };

    public void saveSuperLike(ChannelPostReplyLikeVO channelPostReplyLikeVO){
        channelPostReplyLikeMapper.insertSuperLike(channelPostReplyLikeVO);
    }

    public void deleteReplyLike(@Param("replyId") Long replyId, @Param("memberId") Long memberId){
        channelPostReplyLikeMapper.deleteReplyLike(replyId, memberId);
    };

    public int findReplyLikeCount(Long id){
        return channelPostReplyLikeMapper.selectReplyLikeCount(id);
    };

    public ChannelPostReplyLikeVO findReplyLikeByReplyId(@Param("replyId") Long replyId, @Param("memberId") Long memberId){
        return channelPostReplyLikeMapper.selectReplyLikeByReplyId(replyId, memberId);
    };
}
