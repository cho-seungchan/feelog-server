package com.app.feelog.mapper;

import com.app.feelog.domain.dto.ChannelPostDTO;
import com.app.feelog.domain.dto.ChannelPostSearchDTO;
import com.app.feelog.domain.dto.ChannelPostTagListDTO;
import com.app.feelog.domain.dto.MainPostListDTO;
import com.app.feelog.domain.vo.ChannelPostVO;
import com.app.feelog.util.pagination.PostPagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    List<ChannelPostSearchDTO> searchChannelPosts(String keyword);

    List<ChannelPostSearchDTO> searchChannelPostsCheer(String keyword);

    List<ChannelPostSearchDTO> searchMoreChannelPosts(@Param("keyword") String keyword, @Param("limit") int limit, @Param("offset") int offset);

    List<ChannelPostSearchDTO> searchMoreChannelPostsCheer(String keyword, int limit, int offset);

    public Optional<MainPostListDTO> selectCheerOne();

    public List<MainPostListDTO> selectCheerPostAll(PostPagination postPagination);

//    박정근 :: 포스트 상세 조회
    public Optional<ChannelPostDTO> selectPostByPostId(Long id);

    public Optional<ChannelPostVO> selectNextPost(@Param("channelId") Long channelId, @Param("id")Long id);

    public Optional<ChannelPostVO> selectPreviousPost(@Param("channelId") Long channelId, @Param("id")Long id);

    public int selectSubscribeCount(Long channelId);

    public List<MainPostListDTO> selectPostRandom();

}
