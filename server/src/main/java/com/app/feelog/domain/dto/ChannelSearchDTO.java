package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.SubscribeStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelSearchDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private String nickname;
    private int subscriberCount;
    private String channelUrl;
    private SubscribeStatus SubscribeStatus;
    private boolean subscribed;

}
