package com.app.feelog.domain.vo;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class CommunityPostReplyVO extends ReplyVO {
    @EqualsAndHashCode.Include
    private String community_post_reply_file_path;
    private String community_post_reply_file_name;
    private Long memberId;
    private Long postId;

}
