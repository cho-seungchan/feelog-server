package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.joinDTO.InsertMessageDTO;
import com.app.feelog.domain.dto.joinDTO.MessageInfoDTO;
import com.app.feelog.domain.dto.joinDTO.MessageListDTO;
import com.app.feelog.domain.dto.joinDTO.ReceivceMessageMemberListDTO;

import java.util.List;

public interface MessageService {
    public List<ReceivceMessageMemberListDTO> getReceiveMessageByMemberId(Long memberId);

    public List<MessageListDTO> getMessageListByParticipantId(Long participantId, Long memberId);

    public void insertMessage(InsertMessageDTO insertMessageDTO);

    public void deleteMessage(MessageInfoDTO messageInfoDTO);

    public void deleteMessageListByParticipantId(Long participantId);
}
