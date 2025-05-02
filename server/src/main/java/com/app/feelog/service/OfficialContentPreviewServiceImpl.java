package com.app.feelog.service;

import com.app.feelog.domain.dto.OfficialContentPreviewDTO;
import com.app.feelog.repository.OfficialContentPreviewDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficialContentPreviewServiceImpl implements OfficialContentPreviewService {

    private final OfficialContentPreviewDAO officialContentPreviewDAO;

    @Override
    public List<OfficialContentPreviewDTO> getTop5ByContentType(String type) {
        return officialContentPreviewDAO.findTop5ByContentType(type);
    }

    @Override
    public List<OfficialContentPreviewDTO> findNoticePage(int limit, int offset) {
        return officialContentPreviewDAO.findNoticePage(limit, offset);
    }

    @Override
    public int countNotices() {
        return officialContentPreviewDAO.countNotices();
    }

}
