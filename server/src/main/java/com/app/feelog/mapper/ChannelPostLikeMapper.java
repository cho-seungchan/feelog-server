package com.app.feelog.mapper;

import com.app.feelog.domain.vo.ChannelPostLikeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChannelPostLikeMapper {
    public void insertSuperLike(ChannelPostLikeVO channelPostLikeVO);

    public void insertPostLike(ChannelPostLikeVO channelPostLikeVO);

    public void deletePostLike(@Param("postId") Long postId, @Param("memberId") Long memberId);

    public int selectPostLikeCount(Long postId);

    public ChannelPostLikeVO selectPostLikeByPostAndMemberId(@Param("postId") Long postId, @Param("memberId") Long memberId);

    public List<Long> selectLikeIdsByMemberId(Long memberId);
}
