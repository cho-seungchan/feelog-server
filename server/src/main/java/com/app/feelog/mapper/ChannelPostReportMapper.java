package com.app.feelog.mapper;

import com.app.feelog.domain.dto.ChannelPostReportDTO;
import com.app.feelog.domain.dto.ChannelPostReportListDTO;
import com.app.feelog.domain.vo.ChannelPostReportVO;
import com.app.feelog.domain.vo.ReportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChannelPostReportMapper {
    public void insertReport(ChannelPostReportListDTO channelPostReportListDTO);

    public void insertPostReport(ChannelPostReportListDTO channelPostReportListDTO);

    public List<ChannelPostReportVO> selectReportByMemberId(Long id);

}
