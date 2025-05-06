package com.app.feelog.mapper;

import com.app.feelog.domain.dto.joinDTO.ReceivceMessageMemberListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReceiveMessageMapper {
    public List<ReceivceMessageMemberListDTO> selectReceiveMessageByMemberId(Long memberId);

    public String selectChannelUrl(Long participantId);
}
