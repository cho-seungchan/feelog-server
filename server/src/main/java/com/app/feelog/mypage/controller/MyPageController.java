// 2025.04.17 조승찬  :: 마이페이지

package com.app.feelog.mypage.controller;

import com.app.feelog.domain.dto.ChannelDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.mypage.dto.NotifyCommunityListDTO;
import com.app.feelog.mypage.dto.NotifyReplyListDTO;
import com.app.feelog.mypage.service.MyPageService;
import com.app.feelog.util.SixRowPagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myPage")
@Slf4j
public class MyPageController {
    private final HttpSession session;
    private final HttpServletRequest request;
    private final MyPageService myPageService;
    private final ChannelDTO channelDTO;

    // 2025.04.21  조승찬 :: 프로필 조회
    @GetMapping("/setting-profile")
    public String getSettingProfile(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 멤버 정보 가져오기
        MemberDTO memberDTO = myPageService.getMemberById(member.getId());
        model.addAttribute("member", memberDTO);

        return "myPage/setting-profile";

    }

    // 2025.04.21  조승찬 :: 프로필 수정
    @PostMapping("/setting-profile")
    public String postSettingProfile(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                     MemberDTO memberDTO, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 2025.04.23 조승찬 :: 닉네임 중복 체크
        Optional<MemberDTO> memberOptional = myPageService.getMemberByNickname(memberDTO.getMemberNickname());
        if (memberOptional.isPresent() && !memberOptional.get().getId().equals(member.getId())) {
            model.addAttribute("errorMessage","중복된 Nickname 입니다.");
            model.addAttribute("member", memberDTO);
            return "myPage/setting-profile";
        }

        memberDTO.setId(member.getId());
        myPageService.postSettingProfile(memberDTO);

        return "redirect:/myPage/setting-profile";

    }

    // 2025.04.22 조승찬 :: 알림 설정 조회
    @GetMapping("/setting-notify")
    public String getSettingNotify(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 멤버 정보 가져오기
        MemberDTO memberDTO = myPageService.getMemberById(member.getId());
        model.addAttribute("member", memberDTO);

        return "myPage/setting-notify";
    }


    // 2025.04.22 조승찬 :: 알림 설정 수정
    @PostMapping("/setting-notify")
    public String PostSettingNotify(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    MemberDTO memberDTO) {
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        log.info(memberDTO.toString());
        // 화면에서 한글로 올라온 데이타를 컨퍼터가 이넘 객체로 변환 (** dto 생성자에서 default를 off로 set)
        memberDTO.setId(member.getId());
        myPageService.postSettingNotify(memberDTO);

        return "redirect:/myPage/setting-notify";

    };

    // 2025.04.22 조승찬 :: 채널 만들기 화면
    @GetMapping("/make-channel")
    public String getMakingChannel(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                Model model){

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        ChannelDTO channelDTO = new ChannelDTO();
        model.addAttribute("channel", channelDTO);

        return "myPage/make-channel";
    }

    // 2025.04.22 조승찬 :: 채널 만들기
    @PostMapping("/make-channel")
    public String PostMakingChannel(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                ChannelDTO channelDTO, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 중복 여부 확인. url만 존재하면 중복
        if (myPageService.getChannelByUrl(channelDTO.getChannelUrl()).isPresent()){
            model.addAttribute("errorMessage","중복된 url 입니다.");
            model.addAttribute("channel", channelDTO);
            return "myPage/make-channel";
        }

        // 신규 채널 생성
        channelDTO.setMemberId(member.getId());
        myPageService.postMakingChannel(channelDTO);

        return "redirect:/myPage/read-channel/"+channelDTO.getChannelUrl();
    }

    // 2025.04.22 조승찬 :: 채널 보기(수정, 삭제용)
    @GetMapping("/read-channel/{channelUrl}")
    public String getReadChannel(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                 @PathVariable String channelUrl,
                                 Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        ChannelDTO channel = myPageService.getChannelByUrl(channelUrl)
                .orElse(null);

        model.addAttribute("channel", channel);

        return "myPage/read-channel";
    }

    // 2025.04.23 조승찬 :: 채널 수정
    @PostMapping("/update-channel")
    public String postUpdateChannel(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    ChannelDTO channelDTO, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 중복 여부 확인. id가 다르고 url이 같으면 중복 오류
        Optional<ChannelDTO> channelOptional = myPageService.getChannelByUrl(channelDTO.getChannelUrl());
        if (channelOptional.isPresent() && !channelOptional.get().getId().equals(channelDTO.getId())) {
            model.addAttribute("errorMessage", "중복된 url 입니다.");
            model.addAttribute("channel", channelDTO);
            return "myPage/read-channel";
        }

        // 채널 정보 수정
        myPageService.postUpdateChannel(channelDTO);

        return "redirect:/myPage/read-channel/"+channelDTO.getChannelUrl();
    }

    // 2025.04.23 조승찬 :: 채널 삭제
    @PostMapping("/delete-channel")
    public String postDeleteChannel(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    ChannelDTO channelDTO, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 채널 정보 삭제
        myPageService.postDeleteChannel(channelDTO.getId());

        // 채널 만들기 화면으로 보내기
        ChannelDTO channel = new ChannelDTO();
        model.addAttribute("channel", channel);

        return "myPage/make-channel";
    }

    // 2025.04.23 조승찬 :: 알림 메뉴 중 커뮤니티 목록
    @GetMapping("/notify-community-list")
    public String getNotifyCommunityList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                             Model model, SixRowPagination pagination){

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 목록 가져와서 보여주기
        List<NotifyCommunityListDTO> communities = myPageService.getNotifyCommunityList(member.getId(), pagination);
        model.addAttribute("communities", communities);
        model.addAttribute("pagination", pagination);

        log.info(pagination.toString());

        return "myPage/notify-community-list";
    }

    // 2025.04.23 조승찬 :: 알림 메뉴 중 포스트 댓글 목록
    @GetMapping("/notify-reply-list")
    public String getNotifyReplyList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                         Model model, SixRowPagination pagination){

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 목록 가져와서 보여주기
        List<NotifyReplyListDTO> replies = myPageService.getNotifyReplyList(member.getId(), pagination);
        model.addAttribute("replies", replies);
        model.addAttribute("pagination", pagination);

        return "myPage/notify-community-list";
    }

    @GetMapping("/admin-notice-list")
    public String adminNoticeList(){
        return "myPage/admin-notice-list";
    }

    @GetMapping("/block-list")
    public String blockList(){
        return "myPage/block-list";
    }

    @GetMapping("/community")
    public String community(){
        return "myPage/community";
    }

    @GetMapping("/community-reply")
    public String communityReply(){
        return "myPage/community-reply";
    }

    @GetMapping("/message-list")
    public String messageList(){
        return "myPage/message-list";
    }

    @GetMapping("/notify-admin-list")
    public String notifyAdminList(){
        return "myPage/notify-admin-list";
    }

    @GetMapping("/storage-reply")
    public String storageReply(){
        return "myPage/storage-reply";
    }

    @GetMapping("/storage-scrab")
    public String storageScrab(){
        return "myPage/storage-scrab";
    }

    @GetMapping("/subscribe-list")
    public String subscribeList(){
        return "myPage/subscribe-list";
    }

}
