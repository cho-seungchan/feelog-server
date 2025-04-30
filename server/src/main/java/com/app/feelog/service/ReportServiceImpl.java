package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryReportDTO;
import com.app.feelog.domain.vo.DiaryReportVO;
import com.app.feelog.repository.DiaryReportDAO;
import com.app.feelog.repository.ReportDAO;
import com.app.feelog.service.voToDto.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ReportServiceImpl implements ReportService {
    private final DiaryReportDAO diaryReportDAO;
    private final ReportDAO reportDAO;

    @Override
    public void addDiaryReport(DiaryReportDTO diaryReportDTO) {
        DiaryReportVO diaryReportVO = diaryReportDTO.toVO();
        diaryReportDAO.saveSuperReport(diaryReportVO);
        diaryReportDAO.saveDiaryReport(diaryReportVO);
    }
}
