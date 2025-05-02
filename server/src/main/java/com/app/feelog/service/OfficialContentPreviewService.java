package com.app.feelog.service;

import com.app.feelog.domain.dto.OfficialContentPreviewDTO;

import java.util.List;

public interface OfficialContentPreviewService {

    List<OfficialContentPreviewDTO> getTop5ByContentType(String type);

    List<OfficialContentPreviewDTO> findNoticePage(int limit, int offset);

    int countNotices();

}
