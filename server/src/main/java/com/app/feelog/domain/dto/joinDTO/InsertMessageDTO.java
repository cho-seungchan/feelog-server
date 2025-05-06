package com.app.feelog.domain.dto.joinDTO;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class InsertMessageDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long participantId;
    private String messageContent;
}
