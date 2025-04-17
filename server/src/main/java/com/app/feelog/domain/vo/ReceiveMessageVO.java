package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.ReceiveMessageStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ReceiveMessageVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long senderId;
    private ReceiveMessageStatus receiveMessageStatus;

    @Builder

    public ReceiveMessageVO(Long id, Long memberId, Long senderId, ReceiveMessageStatus receiveMessageStatus) {
        this.id = id;
        this.memberId = memberId;
        this.senderId = senderId;
        this.receiveMessageStatus = receiveMessageStatus;
    }
}
