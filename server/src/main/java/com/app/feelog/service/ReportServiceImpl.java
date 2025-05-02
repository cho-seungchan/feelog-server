package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryReplyReportDTO;
import com.app.feelog.domain.dto.DiaryReportDTO;
import com.app.feelog.domain.vo.DiaryReplyReportVO;
import com.app.feelog.domain.vo.DiaryReportVO;
import com.app.feelog.repository.DiaryReplyReportDAO;
import com.app.feelog.repository.DiaryReportDAO;
import com.app.feelog.repository.ReportDAO;
import com.app.feelog.service.voToDto.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ReportServiceImpl implements ReportService {
    private final DiaryReportDAO diaryReportDAO;
    private final DiaryReplyReportDAO diaryReplyReportDAO;

    @Override
    public void addDiaryReport(DiaryReportDTO diaryReportDTO) {
        DiaryReportVO diaryReportVO = diaryReportDTO.toVO();
        diaryReportDAO.saveSuperReport(diaryReportVO);
        diaryReportDAO.saveDiaryReport(diaryReportVO);
    }

    @Override
    public List<DiaryReplyReportDTO> getDiaryReplyReportListByMemberId(Long memberId) {
        List<DiaryReplyReportVO> replyListVOs = diaryReplyReportDAO.findReportListByMemberId(memberId);
        List<DiaryReplyReportDTO> replyList = replyListVOs.stream().map(this::toDTO).toList();

        return replyList;
    }

    @Override
    public void addDiaryReplyReport(DiaryReplyReportDTO diaryReplyReportDTO) {
        DiaryReplyReportVO diaryReplyReportVO = diaryReplyReportDTO.toVO();
        diaryReplyReportDAO.saveSuperReport(diaryReplyReportVO);
        diaryReplyReportDAO.saveDiaryReplyReport(diaryReplyReportVO);
    }
}
