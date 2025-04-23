package com.app.feelog.repository;

import com.app.feelog.domain.vo.ChannelPostTagVO;
import com.app.feelog.mapper.ChannelPostTagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public void reactivateTagStatus(Long tagId, Long channelPostId) {
        channelPostTagMapper.reactivateTagStatusByPostTag(tagId, channelPostId);
    }

    public Optional<ChannelPostTagVO> findByTagIdAndPostId(Long tagId, Long channelPostId) {
        return channelPostTagMapper.findByTagIdAndPostId(tagId, channelPostId);
    }

    public Long findTagIdByContentAndPostId(String content, Long channelPostId) {
        return channelPostTagMapper.findTagIdByContentAndPostId(content, channelPostId);
    }

}
