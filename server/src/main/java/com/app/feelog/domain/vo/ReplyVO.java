package com.app.feelog.domain.vo;

import com.app.feelog.domain.enumeration.ReplyStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class ReplyVO extends SuperPeriod {
    @EqualsAndHashCode.Include
    private Long id;
    private String replyContent;
    private String replyFilePath;
    private String replyFileName;
    private ReplyStatus replyStatus;

}
