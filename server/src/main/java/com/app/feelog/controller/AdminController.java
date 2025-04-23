package com.app.feelog.controller;

import ch.qos.logback.core.model.Model;
import com.app.feelog.domain.dto.*;
import com.app.feelog.service.AdminServiceImpl;
import com.app.feelog.service.FaqServiceImpl;
import com.app.feelog.service.MemberServiceImpl;
import com.app.feelog.service.NoticeServiceImpl;
import com.app.feelog.util.pagination.AdminPagination;
import com.app.feelog.util.pagination.MemberPagination;
import com.app.feelog.util.pagination.NoticePagination;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final HttpSession session;
    private final AdminServiceImpl adminServiceImpl;
    private final NoticeServiceImpl noticeServiceImpl;
    private final FaqServiceImpl faqServiceImpl;
    private final MemberServiceImpl memberServiceImpl;


    @GetMapping("/admin")
    public String goToAdmin(MemberDTO adminDTO, Model model) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
        if(loginMember == null) {
            return "redirect:/login/email-login";
        }
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
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        noticeDTO.setId(loginMember.getId());
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

    @PostMapping("/addFaq")
    public void addFaq(@RequestBody FaqDTO faqDTO) {
        faqServiceImpl.addFaq(faqDTO);
    }

    @GetMapping("faqLists")
    @ResponseBody
    public FaqListDTO faqLists(NoticePagination noticePagination) {
        return faqServiceImpl.getFaqList(noticePagination);
    }

    @PutMapping("/updateFaq")
    public void updateFaq(@RequestBody FaqDTO faqDTO) {
        faqServiceImpl.updateFaq(faqDTO);
    }

    @PutMapping("/deleteFaq")
    public void deleteFaq(@RequestBody Long id) {
        faqServiceImpl.deleteFaq(id);
    }

    @GetMapping("/memberLists")
    @ResponseBody
    public MemberListDTO getMemberLists(MemberPagination memberPagination) {
        return memberServiceImpl.getMemberLists(memberPagination);
    }

    @PutMapping("/deleteMember")
    public void deleteMember(@RequestBody List<String> idList) {
        idList.forEach((id) -> {
            memberServiceImpl.deleteMember(Long.parseLong(id));
        });
    }

    @PutMapping("/bannedMember")
    public void bannedMember(@RequestBody List<String> idList) {
        idList.forEach((id) -> {
            memberServiceImpl.bannedMember(Long.parseLong(id));
        });
    }

    @PutMapping("/activeMember")
    public void activeMember(@RequestBody List<String> idList) {
        idList.forEach((id) -> {
            memberServiceImpl.activeMember(Long.parseLong(id));
        });
    }

    @GetMapping("/memberList/{keyword}")
    @ResponseBody
    public MemberListDTO getMemberListSearchAll(@Param("pagination") MemberPagination memberPagination, @PathVariable("keyword") String keyword) {
        return memberServiceImpl.getSearchAll(memberPagination, keyword);
    }
}
