package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.DiaryReplyReportDTO;
import com.app.feelog.domain.dto.DiaryReportDTO;
import com.app.feelog.domain.vo.DiaryReplyReportVO;
import com.app.feelog.domain.vo.DiaryReportVO;

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
}
