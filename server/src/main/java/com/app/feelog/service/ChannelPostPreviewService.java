package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostPreviewDTO;

import java.util.List;

public interface ChannelPostPreviewService {

    // 포스트 슬라이드 조회
    List<ChannelPostPreviewDTO> getPostSlides(Long channelId);

    List<ChannelPostPreviewDTO> getCheerSlides(Long channelId);

    List<ChannelPostPreviewDTO> getVisiblePostsWithPagination(Long channelId, int offset, int limit);

    int countVisiblePosts(Long channelId);

    List<ChannelPostPreviewDTO> getVisibleCheersWithPagination(Long channelId, int offset, int limit);

    int countVisibleCheers(Long channelId);

}
