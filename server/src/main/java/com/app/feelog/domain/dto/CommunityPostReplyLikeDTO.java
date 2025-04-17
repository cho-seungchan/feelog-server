package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.CommunityPostReplyLikeVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CommunityPostReplyLikeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long replyId;
    private String createdDate;
    private String updatedDate;

    public CommunityPostReplyLikeVO toVO() {
        return CommunityPostReplyLikeVO.builder()
                .id(id)
                .memberId(memberId)
                .replyId(replyId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }


}
