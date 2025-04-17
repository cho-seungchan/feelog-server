package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.ReplyStatus;
import com.app.feelog.domain.vo.ReplyVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ReplyDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String replyContent;
    private String replyFilePath;
    private String replyFileName;
    private String replyFileSize;
    private ReplyStatus replyStatus;
    private String createdDate;
    private String updatedDate;

    public ReplyVO toVO() {
        return ReplyVO.builder()
                .id(id)
                .replyContent(replyContent)
                .replyFilePath(replyFilePath)
                .replyFileName(replyFileName)
                .replyFileSize(replyFileSize)
                .replyStatus(replyStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
