package com.app.feelog.service;

import com.app.feelog.domain.dto.MessageDTO;
import com.app.feelog.domain.dto.ReceiveMessageDTO;
import com.app.feelog.domain.dto.SendMessageDTO;
import com.app.feelog.domain.dto.joinDTO.InsertMessageDTO;
import com.app.feelog.domain.dto.joinDTO.MessageInfoDTO;
import com.app.feelog.domain.dto.joinDTO.MessageListDTO;
import com.app.feelog.domain.dto.joinDTO.ReceivceMessageMemberListDTO;
import com.app.feelog.domain.vo.MessageVO;
import com.app.feelog.repository.MessageDAO;
import com.app.feelog.repository.ReceiveMessageDAO;
import com.app.feelog.service.voToDto.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MessageServiceImpl implements MessageService {
    private final ReceiveMessageDAO receiveMessageDAO;
    private final MessageDAO messageDAO;

    @Override
    public List<ReceivceMessageMemberListDTO> getReceiveMessageByMemberId(Long memberId) {
        List<ReceivceMessageMemberListDTO> memberList = receiveMessageDAO.findReceiveMessageByMemberId(memberId);
        memberList.forEach((member) -> {
            member.setMemberUrl(receiveMessageDAO.findChannelUrl(member.getParticipantId()));
        });

        return memberList;
    }

    @Override
    public List<MessageListDTO> getMessageListByParticipantId(Long participantId) {
        return messageDAO.findMessageListByParticipantId(participantId);
    }

    @Override
    public void insertMessage(InsertMessageDTO insertMessageDTO) {
        MessageDTO messageDTO = new MessageDTO();
        ReceiveMessageDTO receiveMessageDTO = new ReceiveMessageDTO();
        SendMessageDTO sendMessageDTO = new SendMessageDTO();

        messageDTO.setMessageContent(insertMessageDTO.getMessageContent());
        MessageVO messageVO = messageDTO.toVO();
        messageDAO.saveSuperMessage(messageVO);

        receiveMessageDTO.setId(messageVO.getId());
        receiveMessageDTO.setMemberId(insertMessageDTO.getParticipantId());
        receiveMessageDTO.setSenderId(insertMessageDTO.getMemberId());
        messageDAO.saveReceiveMessage(receiveMessageDTO.toVO());

        sendMessageDTO.setId(messageVO.getId());
        sendMessageDTO.setReceiverId(insertMessageDTO.getParticipantId());
        sendMessageDTO.setMemberId(insertMessageDTO.getMemberId());

        messageDAO.saveSendMessage(sendMessageDTO.toVO());
    }

    @Override
    public void deleteMessage(MessageInfoDTO messageInfoDTO) {
        if (messageInfoDTO.getMessageType().equals("received")) {
            log.info("received로 들어옴");
            messageDAO.deleteReceiveMessage(messageInfoDTO.getId());
        } else {
            messageDAO.deleteSendMessage(messageInfoDTO.getId());
        }
    }

    @Override
    public void deleteMessageListByParticipantId(Long participantId) {
        messageDAO.deleteReceiveMessageListByParticipantId(participantId);
        messageDAO.deleteSendMessageListByParticipantId(participantId);
    }
}
