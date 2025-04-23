package com.app.feelog.mapper;

import com.app.feelog.domain.vo.ChannelPostTagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ChannelPostTagMapper {

    void insert(ChannelPostTagVO vo);

    void deleteByChannelPostId(Long channelPostId);

    List<String> findTagContentsByChannelPostId(Long channelPostId);

    void softDeleteByTagContentAndPostId(String tagContent, Long channelPostId);

    void reactivateTagStatusByPostTag(@Param("tagId") Long tagId, @Param("channelPostId") Long channelPostId);

    Optional<ChannelPostTagVO> findByTagIdAndPostId(@Param("tagId") Long tagId,
                                                    @Param("channelPostId") Long channelPostId);

    Long findTagIdByContentAndPostId(@Param("content") String content,
                                     @Param("channelPostId") Long channelPostId);





}
