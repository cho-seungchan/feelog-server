package com.app.feelog.service;

import com.app.feelog.domain.dto.AdminListDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.NoticeListDTO;
import com.app.feelog.mapper.AdminMapper;
import com.app.feelog.repository.AdminDAO;
import com.app.feelog.util.pagination.AdminPagination;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AdminServiceImplTests {
    @Autowired
    private AdminServiceImpl adminServiceImpl;
    @Autowired
    private NoticeServiceImpl noticeServiceImpl;

    @Test
    public void saveTests(){
        MemberDTO adminDTO = new MemberDTO();

        adminDTO.setMemberEmail("adminService@admin.com");
        adminDTO.setMemberPassword("123412");
        adminDTO.setMemberNickname("adminService");

        adminServiceImpl.saveAdmin(adminDTO);
    }

    @Test
    public void getAdminTest() {
        AdminListDTO adminListDTO = new AdminListDTO();
        AdminPagination adminPagination = new AdminPagination();

        adminListDTO = adminServiceImpl.getAdminAll(adminPagination);

        adminListDTO.getAdminList().forEach(System.out::println);

    }

    @Test
    public void deleteTest() {
        adminServiceImpl.deleteAdmin(8L);
    }

    @Test
    public void getNoticeTest() {
        NoticeListDTO noticeListDTO = new NoticeListDTO();
        NoticePagination noticePagination = new NoticePagination();

        noticeListDTO = noticeServiceImpl.getNoticeLists(noticePagination);

        noticeListDTO.getNoticeList().forEach(System.out::println);
    }
}
