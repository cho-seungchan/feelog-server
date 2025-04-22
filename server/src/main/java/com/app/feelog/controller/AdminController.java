package com.app.feelog.controller;

import com.app.feelog.domain.dto.AdminListDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.NoticeDTO;
import com.app.feelog.domain.dto.NoticeListDTO;
import com.app.feelog.service.AdminServiceImpl;
import com.app.feelog.service.NoticeServiceImpl;
import com.app.feelog.util.pagination.AdminPagination;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final AdminServiceImpl adminServiceImpl;
    private final NoticeServiceImpl noticeServiceImpl;


    @GetMapping("/admin")
    public String goToAdmin(MemberDTO adminDTO) {
        return "/admin/admin";
    };

    @GetMapping("/adminlists")
    @ResponseBody
    public AdminListDTO adminLists(AdminPagination adminPagination){
        return adminServiceImpl.getAdminAll(adminPagination);
    }

    @PostMapping("/addAdmin")
    public void addAdmin(@RequestBody MemberDTO adminDTO) {
        adminServiceImpl.saveAdmin(adminDTO);
    }

    @PutMapping("/deleteAdmin")
    public void deleteAdmin(@RequestBody List<String> idList){
        idList.forEach(id -> {
            adminServiceImpl.deleteAdmin(Long.parseLong(id));
        });
    }

    @PostMapping("/addNotice")
    public void addNotice(@RequestBody NoticeDTO noticeDTO) {
        noticeDTO.setId(2L);
        log.info(noticeDTO.toString());

        noticeServiceImpl.addNotice(noticeDTO);
    }


    @GetMapping("/noticeLists")
    @ResponseBody
    public NoticeListDTO getNoticeList(NoticePagination noticePagination) {
        return noticeServiceImpl.getNoticeLists(noticePagination);
    }

    @PutMapping("/updateNotice")
    public void updateNotice(@RequestBody NoticeDTO noticeDTO) {
        log.info(noticeDTO.toString());
        noticeServiceImpl.updateNotice(noticeDTO);
    }

    @PutMapping("/deleteNotice")
    public void deleteNotice(@RequestBody Long id) {
        noticeServiceImpl.deleteNotice(id);
    }
}
