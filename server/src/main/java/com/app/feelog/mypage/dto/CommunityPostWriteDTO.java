package com.app.feelog.mypage.dto;

import com.app.feelog.domain.vo.CommunityPostFileVO;
import com.app.feelog.domain.vo.CommunityPostVO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CommunityPostWriteDTO {
    @EqualsAndHashCode.Include
    private Long        id;
    private Long        memberId;
    private String      postContent;
    private List<CommunityPostFileVO> files;

    public CommunityPostVO toVO() {
        return CommunityPostVO.builder()
                .id(id)
                .postContent(postContent)
                .memberId(memberId)
                .build();
    }

}
