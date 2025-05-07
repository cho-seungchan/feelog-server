package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.ReceiveMessageStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class ReceiveMessageVO extends MessageVO{
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long senderId;
    private ReceiveMessageStatus receiveMessageStatus;
}
