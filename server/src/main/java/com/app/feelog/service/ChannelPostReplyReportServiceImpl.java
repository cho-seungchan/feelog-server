package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostReplyReportDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryReportListDTO;
import com.app.feelog.domain.dto.joinDTO.PostReportListDTO;
import com.app.feelog.domain.dto.joinDTO.ReplyReportListDTO;
import com.app.feelog.domain.vo.ChannelPostReplyReportVO;
import com.app.feelog.repository.ChannelPostReplyReportDAO;
import com.app.feelog.repository.ReportDAO;
import com.app.feelog.service.voToDto.ChannelPostReplyReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ChannelPostReplyReportServiceImpl implements ChannelPostReplyReportService {
    private final ReportDAO reportDAO;
    private final ChannelPostReplyReportDAO channelPostReplyReportDAO;

    @Override
    public void addReplyReport(ChannelPostReplyReportDTO channelPostReplyReportDTO) {
        ChannelPostReplyReportVO getReportInfo = channelPostReplyReportDTO.toVO();
        log.info("VO변환값 {}", getReportInfo);

        reportDAO.saveReport(getReportInfo);
        channelPostReplyReportDAO.saveReplyReport(getReportInfo);
    }

    @Override
    public ChannelPostReplyReportDTO getReplyReport(Long replyId, Long memberId) {
        return channelPostReplyReportDAO.findReplyReportByMemberAndReplyId(replyId, memberId);
    }

}