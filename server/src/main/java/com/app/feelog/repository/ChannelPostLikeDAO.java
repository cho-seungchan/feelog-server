package com.app.feelog.repository;

import com.app.feelog.domain.vo.ChannelPostLikeVO;
import com.app.feelog.mapper.ChannelPostLikeMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChannelPostLikeDAO {
    private final ChannelPostLikeMapper channelPostLikeMapper;

    public void saveSuperLike(ChannelPostLikeVO channelPostLikeVO){
        channelPostLikeMapper.insertSuperLike(channelPostLikeVO);
    };

    public void savePostLike(ChannelPostLikeVO channelPostLikeVO){
        channelPostLikeMapper.insertPostLike(channelPostLikeVO);
    };

    public void deletePostLike(@Param("postId") Long postId, @Param("memberId") Long memberId){
        channelPostLikeMapper.deletePostLike(postId, memberId);
    };

    public int findPostLikeCount(Long postId){
        return channelPostLikeMapper.selectPostLikeCount(postId);
    };

    public ChannelPostLikeVO findPostLikeByPostAndMemberId(@Param("postId") Long postId, @Param("memberId") Long memberId){
        return channelPostLikeMapper.selectPostLikeByPostAndMemberId(postId, memberId);
    };

    public List<Long> findLikeIdsByMemberId(Long memberId){
        return channelPostLikeMapper.selectLikeIdsByMemberId(memberId);
    };

}
