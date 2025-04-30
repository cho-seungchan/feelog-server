package com.app.feelog.mapper;

import com.app.feelog.domain.vo.DiaryReportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryReportMapper {
    public List<Long> selectDiaryReportByMemberId(Long memberId);

    public void insertDiaryReport(DiaryReportVO diaryReportVO);

    public void insertSuperReport(DiaryReportVO diaryReportVO);
}
