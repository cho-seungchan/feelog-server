package com.app.feelog.mapper;

import com.app.feelog.domain.vo.ChannelPostTagVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ChannelPostTagMapper {

    void insert(ChannelPostTagVO vo);

    void deleteByChannelPostId(Long channelPostId);

    List<String> findTagContentsByChannelPostId(Long channelPostId);

}
