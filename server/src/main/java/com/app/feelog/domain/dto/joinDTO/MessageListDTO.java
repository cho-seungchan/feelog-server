package com.app.feelog.domain.dto.joinDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Getter
@Service
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class MessageListDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long participantId;
    private String messageContent;
    private String updatedDate;
    private String messageType;
    private Long rowNum;
}
