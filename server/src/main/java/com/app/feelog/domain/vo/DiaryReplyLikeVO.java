package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.DiaryReplyLikeStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class DiaryReplyLikeVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long replyId;

    @Builder
    public DiaryReplyLikeVO(String createdDate, String updatedDate, Long id, Long memberId, Long replyId) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberId = memberId;
        this.replyId = replyId;
    }
}