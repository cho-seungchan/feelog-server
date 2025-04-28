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
    private String community_post_reply_file_path;
    private String community_post_reply_file_name;
    private Long memberId;
    private Long postId;
    private String createdDate;
    private String updatedDate;

    public CommunityPostReplyVO toVO() {
        return CommunityPostReplyVO.builder()
                .id(id)
                .replyContent(replyContent)
                .community_post_reply_file_path(community_post_reply_file_path)
                .community_post_reply_file_name(community_post_reply_file_name)
                .memberId(memberId)
                .postId(postId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }

}
