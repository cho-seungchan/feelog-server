package com.app.feelog.repository;

import com.app.feelog.domain.vo.DiaryReportVO;
import com.app.feelog.mapper.DiaryReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiaryReportDAO {
    private final DiaryReportMapper diaryReportMapper;

    public List<Long> findDiaryReportByMemberId(Long memberId){
        return diaryReportMapper.selectDiaryReportByMemberId(memberId);
    };

    public void saveDiaryReport(DiaryReportVO diaryReportVO){
        diaryReportMapper.insertDiaryReport(diaryReportVO);
    };

    public void saveSuperReport(DiaryReportVO diaryReportVO){
        diaryReportMapper.insertSuperReport(diaryReportVO);
    };


}
