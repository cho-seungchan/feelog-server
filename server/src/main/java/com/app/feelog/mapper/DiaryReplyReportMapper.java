package com.app.feelog.mapper;

import com.app.feelog.domain.vo.DiaryReplyReportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryReplyReportMapper {
    public void insertSuperReport(DiaryReplyReportVO diaryReplyReportVO);

    public void insertDiaryReplyReport(DiaryReplyReportVO diaryReplyReportVO);

    public List<DiaryReplyReportVO> selectReportListByMemberId(Long memberId);
}
