// 2025.04.26 조승찬

package com.app.feelog.mypage.controller;

import com.app.feelog.domain.dto.ChannelDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.vo.CommunityPostVO;
import com.app.feelog.mypage.dto.CommunityPostListDTO;
import com.app.feelog.mypage.dto.CommunityPostWriteDTO;
import com.app.feelog.mypage.service.CommunityService;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/feelog.com")
@Slf4j
public class CommunityController {
    private final HttpSession session;
    private final HttpServletRequest request;
    private final CommunityService communityService;

    @GetMapping("/@{channelUrl}/community")
    public String getCommunityPostList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                        @PathVariable String channelUrl, Model model, SixRowPagination pagination){

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 커뮤니티 글 목록 가져오기
        List<CommunityPostListDTO> communities = communityService.getCommunityPostList(member.getId(), channelUrl, pagination);

        CommunityPostWriteDTO communityPost = new CommunityPostWriteDTO();
        model.addAttribute("communities", communities);
        model.addAttribute("pagination", pagination);
        model.addAttribute("currentChannelUrl",channelUrl);
        model.addAttribute("communityPost", communityPost);

        return "community/community";
    }

    // 커뮤니티 글 쓰기
    @PostMapping("/@{channelUrl}/community")
    public String postCommunityPost(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    @PathVariable String channelUrl, CommunityPostWriteDTO communityPostWriteDTO, SixRowPagination pagination) {

        communityPostWriteDTO.setMemberId(member.getId());
        communityService.postCommunityPost(channelUrl, communityPostWriteDTO);

        return "redirect:/feelog.com/@"+channelUrl+"/community";
    }


    @GetMapping("/community-reply")
    public String communityReply(){
        return "community/community-reply";
    }

}
