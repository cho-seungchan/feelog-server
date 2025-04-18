package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class MessageVO extends Period{
    @EqualsAndHashCode.Include
    private Long id;
    private String messageContent;

    @Builder
    public MessageVO(String createdDate, String updatedDate, Long id, String messageContent) {
        super(createdDate, updatedDate);
        this.id = id;
        this.messageContent = messageContent;
    }
}
