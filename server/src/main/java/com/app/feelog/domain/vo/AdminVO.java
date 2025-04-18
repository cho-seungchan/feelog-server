package com.app.feelog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class AdminVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String memberEmail;
    private String memberNickname;

    @Builder
    public AdminVO(Long id, String memberEmail, String memberNickname) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.memberNickname = memberNickname;
    }
}
