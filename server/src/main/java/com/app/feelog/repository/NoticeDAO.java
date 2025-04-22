package com.app.feelog.repository;

import com.app.feelog.domain.dto.NoticeAdminDTO;
import com.app.feelog.domain.vo.NoticeVO;
import com.app.feelog.mapper.NoticeMapper;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeDAO {
    private final NoticeMapper noticeMapper;

    public void svaeNotice(NoticeVO noticeVO) {
        noticeMapper.insertNotice(noticeVO);
    }

    public List<NoticeAdminDTO> findNotice(NoticePagination noticePagination) {
        return noticeMapper.selectNoticeAll(noticePagination);
    }

    public int findNoticeCount() {
        return noticeMapper.selectNoticeCount();
    }

    public void setNotice(NoticeVO noticeVO) {
        noticeMapper.updateNotice(noticeVO);
    }

    public void deleteNotice(Long id) {
        noticeMapper.deleteNotice(id);
    }
}
