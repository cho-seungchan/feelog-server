package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.ReplyStatus;
import com.app.feelog.domain.vo.ChannelPostReplyVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ChannelPostReplyDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String replyContent;
    private String replyFilePath;
    private String replyFileName;
    private ReplyStatus replyStatus;
    private Long memberId;
    private Long postId;
    private Long postMemberId;
    private String createdDate;
    private String updatedDate;

    public ChannelPostReplyVO toVO() {
        return ChannelPostReplyVO.builder()
                .id(id)
                .replyContent(replyContent)
                .replyFilePath(replyFilePath)
                .replyFileName(replyFileName)
                .replyStatus(replyStatus)
                .memberId(memberId)
                .postId(postId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
