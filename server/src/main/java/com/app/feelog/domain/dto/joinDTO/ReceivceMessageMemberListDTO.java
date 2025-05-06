package com.app.feelog.domain.dto.joinDTO;

import com.app.feelog.domain.enumeration.ReceiveMessageStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ReceivceMessageMemberListDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String messageContent;
    private String updatedDate;
    private Long memberId;
    private Long participantId;
    private ReceiveMessageStatus receiveMessageStatus;
    private String memberNickname;
    private String memberFileName;
    private String memberFilePath;
    private String memberUrl;
}
