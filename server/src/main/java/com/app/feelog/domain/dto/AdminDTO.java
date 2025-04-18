package com.app.feelog.domain.dto;

import com.app.feelog.domain.vo.AdminVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class AdminDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String memberEmail;
    private String memberNickname;

    public AdminVO toVO() {
        return AdminVO.builder()
                .id(id)
                .memberEmail(memberEmail)
                .memberNickname(memberNickname)
                .build();
    }
}
