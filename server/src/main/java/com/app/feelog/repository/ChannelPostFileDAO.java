package com.app.feelog.repository;

import com.app.feelog.domain.vo.ChannelPostFileVO;
import com.app.feelog.mapper.ChannelPostFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChannelPostFileDAO {

    private final ChannelPostFileMapper channelPostFileMapper;

    public void save(ChannelPostFileVO vo) {
        channelPostFileMapper.insert(vo);
    }

    public List<ChannelPostFileVO> findByPostId(Long postId) {
        return channelPostFileMapper.selectByPostId(postId);
    }

    public void deleteAllByPostId(Long postId) {
        channelPostFileMapper.deleteByPostId(postId);
    }

}
