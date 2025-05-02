package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostPreviewDTO;
import com.app.feelog.repository.ChannelPostPreviewDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChannelPostPreviewServiceImpl implements ChannelPostPreviewService {


    private final ChannelPostPreviewDAO channelPostPreviewDAO;

    @Override
    public List<ChannelPostPreviewDTO> getPostSlides(Long channelId) {
        return channelPostPreviewDAO.findPostSlides(channelId);
    }

    @Override
    public List<ChannelPostPreviewDTO> getCheerSlides(Long channelId) {
        return channelPostPreviewDAO.findCheerSlides(channelId);
    }

    @Override
    public List<ChannelPostPreviewDTO> getVisiblePostsWithPagination(Long channelId, int offset, int limit) {
        return channelPostPreviewDAO.findPostsWithPagination(channelId, offset, limit);
    }

    @Override
    public int countVisiblePosts(Long channelId) {
        return channelPostPreviewDAO.countPosts(channelId);
    }

    @Override
    public List<ChannelPostPreviewDTO> getVisibleCheersWithPagination(Long channelId, int offset, int limit) {
        return channelPostPreviewDAO.findCheersWithPagination(channelId, offset, limit);
    }

    @Override
    public int countVisibleCheers(Long channelId) {
        return channelPostPreviewDAO.countCheers(channelId);
    }

}
