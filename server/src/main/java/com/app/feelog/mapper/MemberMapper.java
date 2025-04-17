package com.app.feelog.mapper;

import com.app.feelog.domain.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    Optional<MemberDTO> getMemberByEmail(@Param("memberEmail") String memberEmail);

    void kakaoJoin(MemberDTO memberDTO);
}
