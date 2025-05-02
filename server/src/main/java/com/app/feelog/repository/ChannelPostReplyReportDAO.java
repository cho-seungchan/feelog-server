package com.app.feelog.repository;

import com.app.feelog.domain.dto.ChannelPostReplyReportDTO;
import com.app.feelog.domain.vo.ChannelPostReplyReportVO;
import com.app.feelog.mapper.ChannelPostReplyReportMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChannelPostReplyReportDAO {
    private final ChannelPostReplyReportMapper channelPostReplyReportMapper;

    public void saveReplyReport(ChannelPostReplyReportVO channelPostReplyReportVO) {
        channelPostReplyReportMapper.insertReplyReport(channelPostReplyReportVO);
    }

    public ChannelPostReplyReportDTO findReplyReportByMemberAndReplyId(@Param("replyId")Long replyId, @Param("memberId")Long memberId){
        return channelPostReplyReportMapper.selectReplyReportByMemberAndReplyId(replyId, memberId);
    };

    public void deleteReplyReport(@Param("replyId")Long replyId, @Param("memberId") Long memberId){
        channelPostReplyReportMapper.deleteReplyReport(replyId, memberId);
    };
}
