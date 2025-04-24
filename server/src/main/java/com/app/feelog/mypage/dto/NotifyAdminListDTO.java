package com.app.feelog.mypage.dto;

import com.app.feelog.domain.enumeration.NoticeStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class NotifyAdminListDTO {
    @EqualsAndHashCode.Include
    private String      facilityName;
    private String      facilityKindDetailName;
    private String      districtName;
    private String      facilityAddress;
    private String      facilityTellNo;

}