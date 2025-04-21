package com.app.feelog.mapper;

import com.app.feelog.domain.vo.NoticeVO;
import com.app.feelog.util.pagination.NoticePagination;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    public void insertNotice(NoticeVO noticeVO);

    public int selectNoticeCount();

    public List<NoticeVO> selectNoticeAll(NoticePagination noticePagination);
}
