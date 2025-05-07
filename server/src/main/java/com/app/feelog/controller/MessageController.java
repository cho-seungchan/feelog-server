package com.app.feelog.controller;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.joinDTO.InsertMessageDTO;
import com.app.feelog.domain.dto.joinDTO.MessageInfoDTO;
import com.app.feelog.domain.dto.joinDTO.MessageListDTO;
import com.app.feelog.domain.dto.joinDTO.ReceivceMessageMemberListDTO;
import com.app.feelog.service.NotificationService;
import com.app.feelog.service.voToDto.MessageService;
import jakarta.servlet.http.HttpSession;
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
    private final HttpSession session;
    private final NotificationService notificationService;

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
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        messageService.insertMessage(insertMessageDTO);
        
        Long senderId = loginMember.getId();
        Long receiverId = insertMessageDTO.getParticipantId();

        Long receiveMessageId = insertMessageDTO.getId();

        if (!senderId.equals(receiverId)) {
            notificationService.sendReceiveMessageNotification(senderId, receiverId, receiveMessageId);
        }
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
