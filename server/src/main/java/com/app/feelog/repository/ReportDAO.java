package com.app.feelog.repository;

import com.app.feelog.domain.dto.joinDTO.DiaryReportListDTO;
import com.app.feelog.domain.dto.joinDTO.PostReportListDTO;
import com.app.feelog.domain.dto.joinDTO.ReplyReportListDTO;
import com.app.feelog.domain.vo.ChannelPostReplyReportVO;
import com.app.feelog.domain.vo.ReportVO;
import com.app.feelog.mapper.ReportMapper;
import com.app.feelog.util.Pagination;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportDAO {
    private final ReportMapper reportMapper;

    public void saveReport(ChannelPostReplyReportVO channelPostReplyReportVO){
        reportMapper.insertReport(channelPostReplyReportVO);
    };

    public List<PostReportListDTO> findPostReportList(NoticePagination pagination){
        return reportMapper.selectPostReportList(pagination);
    };

    public List<DiaryReportListDTO> findDiaryReportList(NoticePagination pagination){
        return reportMapper.selectDiaryReportList(pagination);
    };

    public List<ReplyReportListDTO> findReplyReportList(NoticePagination pagination){
        return reportMapper.selectReplyReportList(pagination);
    };

    public int selectPostReportCount(){
        return reportMapper.selectPostReportCount();
    };

    public int selectDiaryReportCount(){
        return reportMapper.selectDiaryReportCount();
    };

    public int selectReplyReportCount(){
        return reportMapper.selectReplyReportCount();
    };

    public void deleteReportById(Long id){
        reportMapper.deleteReportById(id);
    };

    public void returnReport(Long id){
        reportMapper.returnReport(id);
    };


}
