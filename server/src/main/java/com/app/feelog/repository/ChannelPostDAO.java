package com.app.feelog.repository;

import com.app.feelog.domain.dto.ChannelPostSearchDTO;
import com.app.feelog.domain.dto.ChannelPostTagListDTO;
import com.app.feelog.domain.dto.MainPostListDTO;
import com.app.feelog.domain.vo.ChannelPostVO;
import com.app.feelog.mapper.ChannelPostMapper;
import com.app.feelog.util.pagination.PostPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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


}
