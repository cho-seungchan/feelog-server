package com.app.feelog.repository;

import com.app.feelog.domain.dto.ChannelPostDTO;
import com.app.feelog.domain.dto.ChannelPostSearchDTO;
import com.app.feelog.domain.dto.ChannelPostTagListDTO;
import com.app.feelog.domain.dto.MainPostListDTO;
import com.app.feelog.domain.vo.ChannelPostVO;
import com.app.feelog.mapper.ChannelPostMapper;
import com.app.feelog.util.pagination.PostPagination;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChannelPostDAO {

    private final ChannelPostMapper channelPostMapper;

    // 등록
    public void insertChannelPost(ChannelPostVO vo) {
        channelPostMapper.insertChannelPost(vo);
    }

    // 수정용 단건 조회
    public ChannelPostVO findById(Long id) {
        return channelPostMapper.findById(id);
    }

    // 수정
    public void updateChannelPost(ChannelPostVO vo) {
        channelPostMapper.updateChannelPost(vo);
    }

    public List<ChannelPostSearchDTO> findRecentPosts() {
        return channelPostMapper.findRecentChannelPosts();
    }


    //  박정근 :: 전체조회, 카운트
    public List<MainPostListDTO> findPostAll(PostPagination postPagination) {
        return channelPostMapper.selectPostAll(postPagination);
    };

    public int findPostCount(){
        return channelPostMapper.selectPostCount();
    };

    public List<ChannelPostTagListDTO> findPostTagByPostId(Long id){
        return channelPostMapper.selectPostTagByPostId(id);
    };

    public int findPostLikeCountByPostId(Long id){
        return channelPostMapper.selectPostLikeCountByPostId(id);
    };

    public int findPostReplyCountByPostId(Long id){
        return channelPostMapper.selectPostReplyCountByPostId(id);
    };


    public List<ChannelPostSearchDTO> searchChannelPosts(String keyword) {
        return channelPostMapper.searchChannelPosts(keyword);
    }


    public List<ChannelPostSearchDTO> searchChannelPostsCheer(String keyword) {
        return channelPostMapper.searchChannelPostsCheer(keyword);
    }

    public List<ChannelPostSearchDTO> searchMoreChannelPosts(String keyword, int limit, int offset) {
        return channelPostMapper.searchMoreChannelPosts(keyword, limit, offset);
    }

    public List<ChannelPostSearchDTO> searchMoreChannelPostsCheer(String keyword, int limit, int offset) {
        return channelPostMapper.searchMoreChannelPostsCheer(keyword, limit, offset);
    }

    public Optional<MainPostListDTO> findCheerOne(){
        return channelPostMapper.selectCheerOne();
    };

    public List<MainPostListDTO> findCheerPostAll(PostPagination postPagination){
        return channelPostMapper.selectCheerPostAll(postPagination);
    };

//    박정근 :: 포스트 상세조회
    public Optional<ChannelPostDTO> findPostByPostId(Long id){
        return channelPostMapper.selectPostByPostId(id);
    };

    public Optional<ChannelPostVO> findNextPost(@Param("channelId") Long channelId, @Param("id")Long id){
        return channelPostMapper.selectNextPost(channelId, id);
    };

    public Optional<ChannelPostVO> findPreviousPost(@Param("channelId") Long channelId, @Param("id")Long id){
        return channelPostMapper.selectPreviousPost(channelId, id);
    };

    public int findSubscribeCount(Long channelId){
        return channelPostMapper.selectSubscribeCount(channelId);
    };
    public List<MainPostListDTO> findPostRandom(){
        return channelPostMapper.selectPostRandom();
    };

    public void setReadCountByPostId(Long id){
        channelPostMapper.updateReadCountByPostId(id);
    };

}
