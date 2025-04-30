package com.app.feelog.repository;

import com.app.feelog.domain.vo.ChannelPostReplyReportVO;
import com.app.feelog.domain.vo.ReportVO;
import com.app.feelog.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReportDAO {
    private final ReportMapper reportMapper;

    public void saveReport(ChannelPostReplyReportVO channelPostReplyReportVO){
        reportMapper.insertReport(channelPostReplyReportVO);
    };

}
