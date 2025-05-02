package com.app.feelog.domain.dto;

import com.app.feelog.domain.enumeration.MemberStatus;
import com.app.feelog.domain.enumeration.ReplyStatus;
import com.app.feelog.domain.enumeration.ReportStatus;
import com.app.feelog.domain.vo.DiaryReplyVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DiaryReplyDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long diaryId;
    private String replyContent;
    private String replyFilePath;
    private String replyFileName;
    private String updatedDate;
    private String createdDate;
    private ReplyStatus replyStatus;

    private String memberNickname;
    private String memberFilePath;
    private String memberFileName;
    private MemberStatus memberStatus;
    private int replyLikeCount;
    private boolean isLiked;

    public DiaryReplyVO toVO() {
        return DiaryReplyVO.builder()
                .id(id)
                .memberId(memberId)
                .diaryId(diaryId)
                .replyContent(replyContent)
                .replyFilePath(replyFilePath)
                .replyFileName(replyFileName)
                .updatedDate(updatedDate)
                .createdDate(createdDate)
                .replyStatus(replyStatus)
                .build();
    }
}