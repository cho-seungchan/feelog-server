package com.app.feelog.controller;

import ch.qos.logback.core.model.Model;
import com.app.feelog.domain.dto.AdminDTO;
import com.app.feelog.domain.dto.AdminListDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.service.AdminService;
import com.app.feelog.util.AdminPagination;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/admin")
    public String goToAdmin(AdminDTO adminDTO) {
        return "/admin/admin";
    };

    @GetMapping("/adminlists")
    @ResponseBody
    public AdminListDTO adminLists(AdminPagination adminPagination){
        return adminService.getAdminAll(adminPagination);
    }

    @PostMapping("/addAdmin")
    public void addAdmin(MemberDTO adminDTO) {
        log.info(adminDTO.toString());
        adminService.saveAdmin(adminDTO);
    }
}
