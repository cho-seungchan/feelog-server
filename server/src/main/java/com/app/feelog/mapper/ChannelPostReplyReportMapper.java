package com.app.feelog.mapper;

import com.app.feelog.domain.dto.ChannelPostReplyReportDTO;
import com.app.feelog.domain.vo.ChannelPostReplyReportVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChannelPostReplyReportMapper {
    public void insertReplyReport(ChannelPostReplyReportVO channelPostReplyReportVO);

    public ChannelPostReplyReportDTO selectReplyReportByMemberAndReplyId(@Param("replyId")Long replyId, @Param("memberId")Long memberId);

    public void deleteReplyReport(@Param("replyId")Long replyId, @Param("memberId") Long memberId);
}
