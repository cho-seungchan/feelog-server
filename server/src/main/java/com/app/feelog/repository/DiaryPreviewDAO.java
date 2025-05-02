package com.app.feelog.repository;

import com.app.feelog.domain.dto.DiaryPreviewDTO;
import com.app.feelog.mapper.DiaryPreviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiaryPreviewDAO {

    private final DiaryPreviewMapper diaryPreviewMapper;


    // 05 01 채널 홈 슬라이드용: 최신 5개
    public List<DiaryPreviewDTO> findVisibleDiariesByChannelUrl(Long viewerId, String channelUrl) {
        return diaryPreviewMapper.findVisibleDiariesByChannelUrl(viewerId, channelUrl);
    }

    // 05 01  마음일기 더보기용: 페이지네이션
    public List<DiaryPreviewDTO> findVisibleDiariesWithPaginationByChannelUrl(Long viewerId, String channelUrl, int offset, int limit) {
        return diaryPreviewMapper.findVisibleDiariesWithPaginationByChannelUrl(viewerId, channelUrl, offset, limit);
    }

    public int countVisibleDiaries(String channelUrl, Long viewerId) {
        return diaryPreviewMapper.countVisibleDiaries(channelUrl, viewerId);
    }

}
