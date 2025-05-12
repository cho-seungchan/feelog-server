package com.app.feelog.repository;

import com.app.feelog.domain.dto.joinDTO.MessageListDTO;
import com.app.feelog.domain.vo.MessageVO;
import com.app.feelog.domain.vo.ReceiveMessageVO;
import com.app.feelog.domain.vo.SendMessageVO;
import com.app.feelog.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessageDAO {
    private final MessageMapper messageMapper;

    public List<MessageListDTO> findMessageListByParticipantId(Long participantId, Long memberId) {
        return messageMapper.selectMessageListByParticipantId(participantId, memberId);
    }

    ;

    public void saveSuperMessage(MessageVO messageVO) {
        messageMapper.insertSuperMessage(messageVO);
    }

    ;

    public void saveReceiveMessage(ReceiveMessageVO receiveMessageVO) {
        messageMapper.insertReceiveMessage(receiveMessageVO);
    }

    ;

    public void saveSendMessage(SendMessageVO sendMessageVO) {
        messageMapper.insertSendMessage(sendMessageVO);
    }

    ;

    public void deleteReceiveMessage(Long id) {
        messageMapper.deleteReceiveMessage(id);
    }

    ;

    public void deleteSendMessage(Long id) {
        messageMapper.deleteSendMessage(id);
    }

    ;

    public void deleteSendMessageListByParticipantId(Long participantId) {
        messageMapper.deleteSendMessageListByParticipantId(participantId);
    }

    ;

    public void deleteReceiveMessageListByParticipantId(Long participantId) {
        messageMapper.deleteReceiveMessageListByParticipantId(participantId);
    }

    ;

}
