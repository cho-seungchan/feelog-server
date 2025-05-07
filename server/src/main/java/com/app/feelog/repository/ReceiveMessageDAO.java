package com.app.feelog.repository;

import com.app.feelog.domain.dto.joinDTO.ReceivceMessageMemberListDTO;
import com.app.feelog.domain.vo.ReceiveMessageVO;
import com.app.feelog.mapper.ReceiveMessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReceiveMessageDAO {
    private final ReceiveMessageMapper receiveMessageMapper;

    public List<ReceivceMessageMemberListDTO> findReceiveMessageByMemberId(Long memberId){
        return receiveMessageMapper.selectReceiveMessageByMemberId(memberId);
    };

    public String findChannelUrl(Long participantId){
        return receiveMessageMapper.selectChannelUrl(participantId);
    };

}
