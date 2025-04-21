package com.app.feelog.mapper;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.util.pagination.AdminPagination;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AdminMapperTests {
    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void insertTest() {
        MemberDTO adminDTO = new MemberDTO();

        adminDTO.setMemberEmail("test123@admin.com");
        adminDTO.setMemberPassword("test1222!");
        adminDTO.setMemberNickname("test123");

        adminMapper.insertAdmin(adminDTO.toVO());
    }

    @Test
    public void selectTest() {
        AdminPagination pagination = new AdminPagination();

        pagination.setPage(1);
        pagination.create(adminMapper.selectAdminCount());
        log.info("{}", pagination.getRealEnd());

        List<MemberVO> admins = adminMapper.selectAdminAll(pagination);
        admins.forEach(admin -> {log.info(admin.toString());});
    }

    @Test
    public void selectCountTest() {
        log.info("count {}",adminMapper.selectAdminCount());
    }
}
