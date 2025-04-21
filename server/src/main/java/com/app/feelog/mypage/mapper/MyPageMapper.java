// 2025.04.21 조승찬
package com.app.feelog.mypage.mapper;

import com.app.feelog.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {

    void postSettingProfile(MemberVO memberVOvo);

}
