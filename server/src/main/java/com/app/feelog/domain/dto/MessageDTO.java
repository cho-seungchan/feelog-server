package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.MessageVO;
import com.app.feelog.domain.vo.Period;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MessageDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String messageContent;
    private String createdDate;
    private String updatedDate;

    public MessageVO toVO() {
        return MessageVO.builder()
                .id(id)
                .messageContent(messageContent)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }

}
