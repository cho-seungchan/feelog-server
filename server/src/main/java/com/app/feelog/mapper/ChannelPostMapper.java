package com.app.feelog.mapper;

import com.app.feelog.domain.dto.ChannelPostSearchDTO;
import com.app.feelog.domain.dto.ChannelPostTagListDTO;
import com.app.feelog.domain.dto.MainPostListDTO;
import com.app.feelog.domain.vo.ChannelPostVO;
import com.app.feelog.util.pagination.PostPagination;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChannelPostMapper {
    // 등록
    void insertChannelPost(ChannelPostVO channelPostVO);

    // 단건 조회 (수정폼용)
    ChannelPostVO selectChannelPostById(Long id);
    ChannelPostVO findById(Long id);

    // 수정
    void updateChannelPost(ChannelPostVO channelPostVO);

    List<ChannelPostSearchDTO> findRecentChannelPosts();

//  박정근 :: 전체조회, 카운트
    public List<MainPostListDTO> selectPostAll(PostPagination postPagination);

    public int selectPostCount();

    public List<ChannelPostTagListDTO> selectPostTagByPostId(Long id);

    public int selectPostLikeCountByPostId(Long id);

    public int selectPostReplyCountByPostId(Long id);
}
