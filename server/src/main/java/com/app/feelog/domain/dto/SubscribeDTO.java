package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.SubscribeStatus;
import com.app.feelog.domain.vo.Period;
import com.app.feelog.domain.vo.SubscribeVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SubscribeDTO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long channelId;
    private Long memberId;
    private SubscribeStatus subscribeStatus;
    private String createdDate;
    private String updatedDate;

    public SubscribeVO toVO() {
        return SubscribeVO.builder()
                .id(id)
                .channelId(channelId)
                .memberId(memberId)
                .subscribeStatus(subscribeStatus)
                .createdDate(createdDate)
                .updatedData(updatedData)
                .build();
    }
}
