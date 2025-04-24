package com.app.feelog.domain.dto;

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

}
