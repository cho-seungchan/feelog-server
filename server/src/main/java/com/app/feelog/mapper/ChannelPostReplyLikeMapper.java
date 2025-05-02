package com.app.feelog.mapper;

import com.app.feelog.domain.vo.ChannelPostReplyLikeVO;
import com.app.feelog.domain.vo.LikeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChannelPostReplyLikeMapper {
    public void insertReplyLike(ChannelPostReplyLikeVO channelPostReplyLikeVO);

    public void insertSuperLike(ChannelPostReplyLikeVO channelPostReplyLikeVO);

    public void deleteReplyLike(@Param("replyId") Long replyId, @Param("memberId") Long memberId);

    public int selectReplyLikeCount(Long id);

    public ChannelPostReplyLikeVO selectReplyLikeByReplyId(@Param("replyId") Long replyId, @Param("memberId") Long memberId);
}
