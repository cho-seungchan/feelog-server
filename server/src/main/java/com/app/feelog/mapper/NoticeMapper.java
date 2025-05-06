package com.app.feelog.mapper;

import com.app.feelog.domain.dto.NoticeAdminDTO;
import com.app.feelog.domain.vo.NoticeVO;
import com.app.feelog.util.pagination.NoticePagination;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NoticeMapper {
    public void insertNotice(NoticeVO noticeVO);

    public int selectNoticeCount();

    public List<NoticeAdminDTO> selectNoticeAll(NoticePagination noticePagination);

    public void updateNotice(NoticeVO noticeVO);

    public void deleteNotice(Long id);

    public List<NoticeVO> selectNotice4();

    public Optional<NoticeVO> selectNoticeDetailById(Long id);

    public Optional<NoticeVO> nextNotice(Long id);

    public Optional<NoticeVO> previousNotice(Long id);

    public void updateReadCount(Long id);

}
