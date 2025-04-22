// 2025.04.21 조승찬
package com.app.feelog.mypage.mapper;

import com.app.feelog.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MyPageMapper {

    // 2025.04.21  조승찬 :: 프로필 수정
    void postSettingProfile(MemberVO memberVOvo);

    // 2025.04.21 조승찬 :: 아이디로 멤버 정보 가져오기
    Optional<MemberVO> getMemberById(Long id);

    // 2025.04.22  조승찬 :: 알림정보 수정
    void postSettingNotify(MemberVO memberVO);
}
