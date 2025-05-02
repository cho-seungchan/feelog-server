package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.ChannelPostReplyDTO;
import com.app.feelog.domain.dto.ChannelPostReplyReportDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryReportListDTO;
import com.app.feelog.domain.dto.joinDTO.PostReportListDTO;
import com.app.feelog.domain.dto.joinDTO.ReplyReportListDTO;
import com.app.feelog.domain.vo.ChannelPostReplyReportVO;
import com.app.feelog.domain.vo.ChannelPostReplyVO;

import java.util.List;

public interface ChannelPostReplyReportService {
    public void addReplyReport(ChannelPostReplyReportDTO channelPostReplyReportDTO);

    public ChannelPostReplyReportDTO getReplyReport(Long replyId, Long memberId);

    public default ChannelPostReplyReportDTO toDTO(ChannelPostReplyReportVO channelPostReplyReportVO){
        ChannelPostReplyReportDTO channelPostReplyReportDTO = new ChannelPostReplyReportDTO();
        if(channelPostReplyReportVO != null){
            channelPostReplyReportDTO.setReplyId(channelPostReplyReportVO.getReplyId());
            channelPostReplyReportDTO.setMemberId(channelPostReplyReportVO.getMemberId());
            channelPostReplyReportDTO.setId(channelPostReplyReportVO.getId());
            channelPostReplyReportDTO.setCreatedDate(channelPostReplyReportVO.getCreatedDate());
            channelPostReplyReportDTO.setUpdatedDate(channelPostReplyReportVO.getUpdatedDate());
        }
        return channelPostReplyReportDTO;
    }

}
