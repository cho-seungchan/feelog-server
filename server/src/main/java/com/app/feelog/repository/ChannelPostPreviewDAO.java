package com.app.feelog.repository;

import com.app.feelog.domain.dto.ChannelPostPreviewDTO;
import com.app.feelog.mapper.ChannelPostPreviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChannelPostPreviewDAO {

    private final ChannelPostPreviewMapper channelPostPreviewMapper;

    // 포스트 조회
    public List<ChannelPostPreviewDTO> findPostSlides(Long channelId) {
        return channelPostPreviewMapper.findPostSlides(channelId);
    }

    public List<ChannelPostPreviewDTO> findCheerSlides(Long channelId) {
        return channelPostPreviewMapper.findCheerSlides(channelId);
    }

    public List<ChannelPostPreviewDTO> findPostsWithPagination(Long channelId, int offset, int limit) {
        return channelPostPreviewMapper.findPostsWithPagination(channelId, offset, limit);
    }

    public int countPosts(Long channelId) {
        return channelPostPreviewMapper.countPosts(channelId);
    }

    public List<ChannelPostPreviewDTO> findCheersWithPagination(Long channelId, int offset, int limit) {
        return channelPostPreviewMapper.findCheersWithPagination(channelId, offset, limit);
    }

    public int countCheers(Long channelId) {
        return channelPostPreviewMapper.countCheers(channelId);
    }
}
