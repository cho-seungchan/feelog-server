package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.ReceiveMessageStatus;
import com.app.feelog.domain.vo.ReceiveMessageVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReceiveMessageDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long senderId;
    private ReceiveMessageStatus receiveMessageStatus;
    private String messageContent;
    private String createdDate;
    private String updatedDate;

    public ReceiveMessageVO toVO() {
        return ReceiveMessageVO.builder()
                .id(id)
                .memberId(memberId)
                .senderId(senderId)
                .receiveMessageStatus(receiveMessageStatus)
                .messageContent(messageContent)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
