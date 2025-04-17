package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.ChannelPostStatus;
import com.app.feelog.domain.enumeration.PostType;
import com.app.feelog.domain.vo.ChannelPostVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelPostDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private PostType postType;
    private String postFilePath;
    private String postFileName;
    private String postFileSize;
    private Long memberId;
    private Long channelId;
    private ChannelPostStatus channelPostStatus;
    private String createdDate;
    private String updatedDate;

    public ChannelPostVO toVO() {
        return ChannelPostVO.builder()
                .id(id)
                .postType(postType)
                .postFilePath(postFilePath)
                .postFileName(postFileName)
                .postFileSize(postFileSize)
                .memberId(memberId)
                .channelId(channelId)
                .channelPostStatus(channelPostStatus)
                .createdDate(createdDate)
                .updatedData(updatedDate)
                .build();
    }
}