package com.app.feelog.repository;

import com.app.feelog.domain.dto.NoticeAdminDTO;
import com.app.feelog.domain.vo.NoticeVO;
import com.app.feelog.mapper.NoticeMapper;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public List<NoticeVO> findNotice4() {
        return noticeMapper.selectNotice4();
    }

    public Optional<NoticeVO> findNoticeDetailById(Long id) {
        return noticeMapper.selectNoticeDetailById(id);
    }

    public Optional<NoticeVO> nextNotice(Long id) {
        return noticeMapper.nextNotice(id);
    }

    ;

    public Optional<NoticeVO> previousNotice(Long id) {
        return noticeMapper.previousNotice(id);
    }

    public void setReadCount(Long id) {
        noticeMapper.updateReadCount(id);
    }

    ;;;;

}
