package com.app.feelog.repository;

import com.app.feelog.domain.vo.ChannelPostTagVO;
import com.app.feelog.mapper.ChannelPostTagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChannelPostTagDAO {

    private final ChannelPostTagMapper channelPostTagMapper;

    public void save(ChannelPostTagVO vo) {
        channelPostTagMapper.insert(vo);
    }

    public void deleteAllByChannelPostId(Long channelPostId) {
        channelPostTagMapper.deleteByChannelPostId(channelPostId);
    }

    public List<String> findTagContentsByChannelPostId(Long channelPostId) {
        return channelPostTagMapper.findTagContentsByChannelPostId(channelPostId);
    }

}
