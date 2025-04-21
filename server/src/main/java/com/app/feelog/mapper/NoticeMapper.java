package com.app.feelog.mapper;

import com.app.feelog.domain.dto.NoticeAdminDTO;
import com.app.feelog.domain.vo.NoticeVO;
import com.app.feelog.util.pagination.NoticePagination;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    public void insertNotice(NoticeVO noticeVO);

    public int selectNoticeCount();

    public List<NoticeAdminDTO> selectNoticeAll(NoticePagination noticePagination);

    public void updateNotice(NoticeVO noticeVO);

    public void deleteNotice(Long id);
}
