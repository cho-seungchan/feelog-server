package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryPreviewDTO;
import com.app.feelog.repository.DiaryPreviewDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiaryPreviewServiceImpl implements DiaryPreviewService {

    private final DiaryPreviewDAO diaryPreviewDAO;

    // 05 01
    @Override
    public List<DiaryPreviewDTO> getVisibleDiariesForSlide(String channelUrl, Long viewerId) {
        return diaryPreviewDAO.findVisibleDiariesByChannelUrl(viewerId, channelUrl);
    }

    // 05 01
    @Override
    public List<DiaryPreviewDTO> getVisibleDiariesWithPagination(String channelUrl, Long viewerId, int offset, int limit) {
        return diaryPreviewDAO.findVisibleDiariesWithPaginationByChannelUrl(viewerId, channelUrl, offset, limit);
    }

    @Override
    public int countVisibleDiaries(String channelUrl, Long viewerId) {
        return diaryPreviewDAO.countVisibleDiaries(channelUrl, viewerId);
    }


}
