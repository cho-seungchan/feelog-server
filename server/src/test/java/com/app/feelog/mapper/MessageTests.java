package com.app.feelog.mapper;

import com.app.feelog.domain.dto.joinDTO.InsertMessageDTO;
import com.app.feelog.domain.dto.joinDTO.ReceivceMessageMemberListDTO;
import com.app.feelog.service.voToDto.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class MessageTests {
    @Autowired
    private ReceiveMessageMapper receiveMessageMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private MessageService messageService;
    @Autowired
    private InsertMessageDTO insertMessageDTO;

    @Test
    public void selectReceiveMessageByMemberId() {
        List<ReceivceMessageMemberListDTO> receiveMessageVO = receiveMessageMapper.selectReceiveMessageByMemberId(23L);
        receiveMessageVO.forEach(System.out::println);
    }

//    @Test
//    public void selectReceiveMessageByMemberId2() {
//        List<MessageListDTO> messageList = messageMapper.selectMessageListByParticipantId(23L);
//        messageList.forEach(System.out::println);
//    }

//    @Test
//    public void selectReceiveMessageByMemberId3() {
//        List<MessageListDTO> messageList = messageService.getMessageListByParticipantId(23L);
//        messageList.forEach(System.out::println);
//    }

    @Test
    public void insertTest2() {
        InsertMessageDTO insertMessageDTO = new InsertMessageDTO();

        insertMessageDTO.setMessageContent("test");
        insertMessageDTO.setMemberId(24L);
        insertMessageDTO.setParticipantId(23L);

        messageService.insertMessage(insertMessageDTO);
    }
}
