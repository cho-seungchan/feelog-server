package com.app.feelog.controller;

import com.app.feelog.domain.dto.joinDTO.InsertMessageDTO;
import com.app.feelog.domain.dto.joinDTO.MessageInfoDTO;
import com.app.feelog.domain.dto.joinDTO.MessageListDTO;
import com.app.feelog.domain.dto.joinDTO.ReceivceMessageMemberListDTO;
import com.app.feelog.service.voToDto.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/list")
    @ResponseBody
    public List<ReceivceMessageMemberListDTO> getReceiveMessageByMemberId(Long memberId) {
        return messageService.getReceiveMessageByMemberId(memberId);
    }

    @GetMapping("/messageList")
    @ResponseBody
    public List<MessageListDTO> getMessageListByParticipantId(Long participantId) {
        return messageService.getMessageListByParticipantId(participantId);
    }

    @PostMapping("/insertMessage")
    public void insertMessage(@RequestBody InsertMessageDTO insertMessageDTO) {
        log.info("insertMessage = {}", insertMessageDTO);
        messageService.insertMessage(insertMessageDTO);
    }

    @PutMapping("/deleteMessage")
    public void deleteMessage(@RequestBody MessageInfoDTO messageInfoDTO) {
        messageService.deleteMessage(messageInfoDTO);
    }

    @PutMapping("deleteMessageListByParticipantId")
    public void deleteMessageListByParticipantId(@RequestBody Long participantId) {
        log.info("deleteMessageListByParticipantId = {}", participantId);
        messageService.deleteMessageListByParticipantId(participantId);
    }
}
