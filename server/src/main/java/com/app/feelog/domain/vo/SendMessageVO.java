package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.SendMessageStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class SendMessageVO extends MessageVO{
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long receiverId;
    private SendMessageStatus sendMessageStatus;
}
