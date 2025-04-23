package com.app.feelog.mapper;

import com.app.feelog.domain.dto.MemberTaskPoolDTO;
import com.app.feelog.domain.vo.MemberTaskPoolVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class MemberTaskPoolTests {
    @Autowired
    private MemberTaskPoolMapper memberTaskPoolMapper;

    @Test
    public void insertMemberTaskPool() {
        MemberTaskPoolDTO memberTaskPoolDTO = new MemberTaskPoolDTO();

        memberTaskPoolDTO.setMemberTaskPoolContent("빨래하기");

        memberTaskPoolMapper.insertMemberTaskPool(memberTaskPoolDTO.toVO());
    }

    @Test
    public void selectMemberTaskPool() {
        Optional<MemberTaskPoolVO> memberTaskPool = memberTaskPoolMapper.selectMemberTaskPoolById(7L);

        memberTaskPool.ifPresent(System.out::println);
    }
}
