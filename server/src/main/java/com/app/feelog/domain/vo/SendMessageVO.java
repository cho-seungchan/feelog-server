package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.SendMessageStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class SendMessageVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long receiverId;
    private SendMessageStatus sendMessageStatus;

    @Builder

    public SendMessageVO(Long id, Long memberId, Long receiverId, SendMessageStatus sendMessageStatus) {
        this.id = id;
        this.memberId = memberId;
        this.receiverId = receiverId;
        this.sendMessageStatus = sendMessageStatus;
    }
}
