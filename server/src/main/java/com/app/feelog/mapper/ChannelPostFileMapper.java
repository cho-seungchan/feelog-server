package com.app.feelog.mapper;

import com.app.feelog.domain.vo.ChannelPostFileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChannelPostFileMapper {

    void insert(ChannelPostFileVO vo);

    List<ChannelPostFileVO> selectByPostId(Long postId);

    void deleteByPostId(Long postId);

    List<Long> findFileIdsByPostId(@Param("postId") Long postId);

    void insertChannelPostFile(ChannelPostFileVO vo);
}
