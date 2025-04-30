package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.CommunityPostReplyVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CommunityPostReplyDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String replyContent;
    private String replyFilePath;
    private String replyFileName;
    private Long memberId;
    private Long postId;
    private String createdDate;
    private String updatedDate;

    public CommunityPostReplyVO toVO() {
        return CommunityPostReplyVO.builder()
                .id(id)
                .replyContent(replyContent)
                .replyFilePath(replyFilePath)
                .replyFileName(replyFileName)
                .memberId(memberId)
                .postId(postId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }

}
