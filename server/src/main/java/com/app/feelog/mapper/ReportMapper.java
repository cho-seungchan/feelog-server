package com.app.feelog.mapper;

import com.app.feelog.domain.vo.ChannelPostReplyReportVO;
import com.app.feelog.domain.vo.ReportVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportMapper {
    public void insertReport(ChannelPostReplyReportVO channelPostReplyReportVO);
}
