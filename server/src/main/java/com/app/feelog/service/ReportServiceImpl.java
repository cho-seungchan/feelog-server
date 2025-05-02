package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryReplyReportDTO;
import com.app.feelog.domain.dto.DiaryReportDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryReportListDTO;
import com.app.feelog.domain.dto.joinDTO.PostReportListDTO;
import com.app.feelog.domain.dto.joinDTO.ReplyReportListDTO;
import com.app.feelog.domain.dto.joinDTO.ReportListDTO;
import com.app.feelog.domain.vo.DiaryReplyReportVO;
import com.app.feelog.domain.vo.DiaryReportVO;
import com.app.feelog.repository.DiaryReplyReportDAO;
import com.app.feelog.repository.DiaryReportDAO;
import com.app.feelog.repository.ReportDAO;
import com.app.feelog.service.voToDto.ReportService;
import com.app.feelog.util.Pagination;
import com.app.feelog.util.pagination.NoticePagination;
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
    private final ReportDAO reportDAO;

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


    @Override
    public ReportListDTO getPostReportList(NoticePagination pagination) {
        ReportListDTO reportListDTO = new ReportListDTO();

        pagination.create(reportDAO.selectPostReportCount());
        reportListDTO.setPagination(pagination);
        reportListDTO.setPostReportList(reportDAO.findPostReportList(pagination));

        return reportListDTO;
    }

    @Override
    public ReportListDTO getDiaryReportList(NoticePagination pagination) {
        ReportListDTO reportListDTO = new ReportListDTO();

        pagination.create(reportDAO.selectDiaryReportCount());
        reportListDTO.setPagination(pagination);
        reportListDTO.setDiaryReportList(reportDAO.findDiaryReportList(pagination));

        return reportListDTO;
    }

    @Override
    public ReportListDTO getReplyReportList(NoticePagination pagination) {
        ReportListDTO reportListDTO = new ReportListDTO();

        pagination.create(reportDAO.selectReplyReportCount());
        reportListDTO.setPagination(pagination);
        reportListDTO.setReplyReportList(reportDAO.findReplyReportList(pagination));

        return reportListDTO;
    }

    @Override
    public void deleteReportById(Long id) {
        reportDAO.deleteReportById(id);
    }

    @Override
    public void returnReport(Long id) {
        reportDAO.returnReport(id);
    }
}
