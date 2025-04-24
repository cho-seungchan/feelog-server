package com.app.feelog.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelPostTagListDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String tagContent;
    private String tagStatus;
    private Long postId;
}
