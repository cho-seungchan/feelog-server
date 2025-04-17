package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.ReplyStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class ReplyVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String replyContent;
    private String replyFilePath;
    private String replyFileName;
    private String replyFileSize;
    private ReplyStatus replyStatus;

    @Builder
    public ReplyVO(String createdDate, String updatedDate, Long id, String replyContent, String replyFilePath, String replyFileName, String replyFileSize, ReplyStatus replyStatus) {
        super(createdDate, updatedDate);
        this.id = id;
        this.replyContent = replyContent;
        this.replyFilePath = replyFilePath;
        this.replyFileName = replyFileName;
        this.replyFileSize = replyFileSize;
        this.replyStatus = replyStatus;
    }
}
