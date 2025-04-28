package com.app.feelog.mapper;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.MemberListDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.util.pagination.MemberPagination;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class MemberMapperTests {
    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void insertTests(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("pakr12@example.com");
        memberDTO.setMemberPassword("test123123!@");
        memberDTO.setMemberNickname("parkjk");
        memberMapper.emailJoin(memberDTO.toVO());
    }

    @Test
    public void selectTest(){
        MemberDTO memberDTO = new MemberDTO();
        MemberPagination memberPagination = new MemberPagination();
        String keyword = "test";
        memberPagination.setPage(1);
        memberPagination.create(memberMapper.selectSearchAllCount(keyword));

        log.info(memberPagination.toString());
        List<MemberVO> memberList = memberMapper.selectSearchAll(memberPagination, keyword);

        memberList.forEach(System.out::println);
    }

}
