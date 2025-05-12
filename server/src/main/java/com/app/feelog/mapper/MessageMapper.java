package com.app.feelog.mapper;

import com.app.feelog.domain.dto.joinDTO.MessageListDTO;
import com.app.feelog.domain.vo.MessageVO;
import com.app.feelog.domain.vo.ReceiveMessageVO;
import com.app.feelog.domain.vo.SendMessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    public List<MessageListDTO> selectMessageListByParticipantId(Long participantId, Long memberId);

    public void insertSuperMessage(MessageVO messageVO);

    public void insertReceiveMessage(ReceiveMessageVO receiveMessageVO);

    public void insertSendMessage(SendMessageVO sendMessageVO);

    public void deleteReceiveMessage(Long id);

    public void deleteSendMessage(Long id);

    public void deleteSendMessageListByParticipantId(Long participantId);

    public void deleteReceiveMessageListByParticipantId(Long participantId);
}
