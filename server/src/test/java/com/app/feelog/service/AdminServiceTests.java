package com.app.feelog.service;

import com.app.feelog.domain.dto.AdminDTO;
import com.app.feelog.domain.dto.AdminListDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.repository.AdminDAO;
import com.app.feelog.util.AdminPagination;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AdminServiceTests {
    @Autowired
    private AdminService adminService;
    private AdminDAO adminDAO;

    @Test
    public void saveTests(){
        MemberDTO adminDTO = new MemberDTO();

        adminDTO.setMemberEmail("adminService@admin.com");
        adminDTO.setMemberPassword("123412");
        adminDTO.setMemberNickname("adminService");

        adminService.saveAdmin(adminDTO);
    }

    @Test
    public void getAdminTest() {
        AdminListDTO adminListDTO = new AdminListDTO();
        AdminPagination adminPagination = new AdminPagination();

        adminListDTO =adminService.getAdminAll(adminPagination);

        log.info(adminListDTO.toString());

    }
}
