package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.PostStatus;
import com.app.feelog.domain.enumeration.PostType;
import com.app.feelog.domain.vo.ChannelPostVO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private PostStatus postStatus;
    private String createdDate;
    private String updatedDate;

    // 첨부파일 및 태그
    private List<Long> fileIds;      // summernote용
    private List<String> tags;       // 새 태그 문자열 리스트
    private String postTitle;
    private String postContent;
    private List<ChannelPostFileDTO> fileList;

    public ChannelPostVO toVO() {
        return ChannelPostVO.builder()
                .id(id)
                .postType(postType)
                .postFilePath(postFilePath)
                .postFileName(postFileName)
                .postFileSize(postFileSize)
                .postContent(postContent)
                .postTitle(postTitle)
                .memberId(memberId)
                .channelId(channelId)
                .postStatus(postStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}