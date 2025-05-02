package com.app.feelog.mapper;

import com.app.feelog.domain.dto.joinDTO.DiaryReportListDTO;
import com.app.feelog.domain.dto.joinDTO.PostReportListDTO;
import com.app.feelog.domain.dto.joinDTO.ReplyReportListDTO;
import com.app.feelog.domain.vo.ChannelPostReplyReportVO;
import com.app.feelog.domain.vo.ReportVO;
import com.app.feelog.util.Pagination;
import com.app.feelog.util.pagination.NoticePagination;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    public void insertReport(ChannelPostReplyReportVO channelPostReplyReportVO);

    public List<PostReportListDTO> selectPostReportList(NoticePagination pagination);

    public List<DiaryReportListDTO> selectDiaryReportList(NoticePagination pagination);

    public List<ReplyReportListDTO> selectReplyReportList(NoticePagination pagination);

    public int selectPostReportCount();

    public int selectDiaryReportCount();

    public int selectReplyReportCount();

    public void deleteReportById(Long id);

    public void returnReport(Long id);
}
