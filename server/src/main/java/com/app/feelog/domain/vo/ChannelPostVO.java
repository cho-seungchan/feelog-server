package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.PostType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class ChannelPostVO extends PostJKVO{
    @EqualsAndHashCode.Include
    private Long   id;
    private PostType postType;
    private String postFilePath;
    private String postFileName;
    private String postFileSize;
    private Long   memberId;
    private Long   channelId;
}