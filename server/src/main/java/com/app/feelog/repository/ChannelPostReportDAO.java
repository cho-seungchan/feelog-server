package com.app.feelog.repository;

import com.app.feelog.domain.dto.ChannelPostReportDTO;
import com.app.feelog.domain.dto.ChannelPostReportListDTO;
import com.app.feelog.domain.vo.ChannelPostReportVO;
import com.app.feelog.domain.vo.ReportVO;
import com.app.feelog.mapper.ChannelPostReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChannelPostReportDAO {
    private final ChannelPostReportMapper channelPostReportMapper;

    public void saveChannelPostReport(ChannelPostReportListDTO channelPostReportListDTO){
        channelPostReportMapper.insertPostReport(channelPostReportListDTO);
    };

    public void saveReport(ChannelPostReportListDTO channelPostReportListDTO){
        channelPostReportMapper.insertReport(channelPostReportListDTO);
    };

    public List<ChannelPostReportVO> findReportByMemberId(Long id){
        return channelPostReportMapper.selectReportByMemberId(id);
    };

}
