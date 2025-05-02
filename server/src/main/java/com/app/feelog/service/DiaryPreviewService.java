package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryPreviewDTO;

import java.util.List;

public interface DiaryPreviewService {

    // 05 01 채널 홈 슬라이드용
    List<DiaryPreviewDTO> getVisibleDiariesForSlide(String channelUrl, Long viewerId);

    // 05 01 마음일기 페이지 더보기용
    List<DiaryPreviewDTO> getVisibleDiariesWithPagination(String channelUrl, Long viewerId, int offset, int limit);

    int countVisibleDiaries(String channelUrl, Long viewerId);

}


