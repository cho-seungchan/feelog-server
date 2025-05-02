package com.app.feelog.repository;

import com.app.feelog.domain.vo.DiaryReplyReportVO;
import com.app.feelog.mapper.DiaryReplyReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiaryReplyReportDAO {
    private final DiaryReplyReportMapper diaryReplyReportMapper;

    public void saveSuperReport(DiaryReplyReportVO diaryReplyReportVO){
        diaryReplyReportMapper.insertSuperReport(diaryReplyReportVO);
    };

    public void saveDiaryReplyReport(DiaryReplyReportVO diaryReplyReportVO){
        diaryReplyReportMapper.insertDiaryReplyReport(diaryReplyReportVO);
    };

    public List<DiaryReplyReportVO> findReportListByMemberId(Long memberId){
        return diaryReplyReportMapper.selectReportListByMemberId(memberId);
    };
}
