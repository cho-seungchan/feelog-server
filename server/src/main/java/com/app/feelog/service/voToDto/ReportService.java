package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.DiaryReplyReportDTO;
import com.app.feelog.domain.dto.DiaryReportDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryReportListDTO;
import com.app.feelog.domain.dto.joinDTO.PostReportListDTO;
import com.app.feelog.domain.dto.joinDTO.ReplyReportListDTO;
import com.app.feelog.domain.dto.joinDTO.ReportListDTO;
import com.app.feelog.domain.vo.DiaryReplyReportVO;
import com.app.feelog.domain.vo.DiaryReportVO;
import com.app.feelog.util.Pagination;
import com.app.feelog.util.pagination.NoticePagination;

import java.util.List;

public interface ReportService {
    public void addDiaryReport(DiaryReportDTO diaryReportDTO);

    public List<DiaryReplyReportDTO> getDiaryReplyReportListByMemberId(Long memberId);

    public void addDiaryReplyReport(DiaryReplyReportDTO diaryReplyReportDTO);

    public default DiaryReplyReportDTO toDTO(DiaryReplyReportVO diaryReplyReportVO){
        DiaryReplyReportDTO diaryReplyReportDTO = new DiaryReplyReportDTO();
        if (diaryReplyReportVO != null) {
            diaryReplyReportDTO.setId(diaryReplyReportVO.getId());
            diaryReplyReportDTO.setMemberId(diaryReplyReportVO.getMemberId());
            diaryReplyReportDTO.setReplyId(diaryReplyReportVO.getReplyId());
            diaryReplyReportDTO.setReportStatus(diaryReplyReportVO.getReportStatus());
            diaryReplyReportDTO.setCreatedDate(diaryReplyReportVO.getCreatedDate());
            diaryReplyReportDTO.setUpdatedDate(diaryReplyReportVO.getUpdatedDate());
        }
        return diaryReplyReportDTO;
    }

    public ReportListDTO getPostReportList(NoticePagination pagination);

    public ReportListDTO getDiaryReportList(NoticePagination pagination);

    public ReportListDTO getReplyReportList(NoticePagination pagination);

    public void deleteReportById(Long id);

    public void returnReport(Long id);
}
