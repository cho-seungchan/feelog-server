package com.app.feelog.repository;

import com.app.feelog.domain.dto.OfficialContentPreviewDTO;
import com.app.feelog.mapper.OfficialContentPreviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OfficialContentPreviewDAO {

    private final OfficialContentPreviewMapper officialContentPreviewMapper;

    public List<OfficialContentPreviewDTO> findTop5ByContentType(String type) {
        return officialContentPreviewMapper.findTop5ByContentType(type);
    }

    public List<OfficialContentPreviewDTO> findNoticePage(int limit, int offset) {
        return officialContentPreviewMapper.findNoticePage(limit, offset);
    }

    public int countNotices() {
        return officialContentPreviewMapper.countNotices();
    }

}
