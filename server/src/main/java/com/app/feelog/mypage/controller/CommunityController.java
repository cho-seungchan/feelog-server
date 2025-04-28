// 2025.04.26 조승찬

package com.app.feelog.mypage.controller;

import com.app.feelog.domain.dto.ChannelDTO;
import com.app.feelog.domain.dto.CommunityPostReplyDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.vo.CommunityPostVO;
import com.app.feelog.mypage.dto.CommunityPostListDTO;
import com.app.feelog.mypage.dto.CommunityPostReplyListDTO;
import com.app.feelog.mypage.dto.CommunityPostWriteDTO;
import com.app.feelog.mypage.service.CommunityService;
import com.app.feelog.mypage.service.MyPageService;
import com.app.feelog.util.SixRowPagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/feelog.com")
@Slf4j
public class CommunityController {
    private final HttpSession session;
    private final HttpServletRequest request;
    private final CommunityService communityService;

    // 2025.04.26 조승찬 :: 커뮤니티 글 목록
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
        model.addAttribute("loginId", member.getId());
        model.addAttribute("communityPost", communityPost);

        return "community/community";
    }

    // 2025.04.26 조승찬 :: 커뮤니티 글 쓰기
    @PostMapping("/@{channelUrl}/community")
    public String postCommunityPost(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    @PathVariable String channelUrl, CommunityPostWriteDTO communityPostWriteDTO, SixRowPagination pagination) {

        communityPostWriteDTO.setMemberId(member.getId());
        communityService.postCommunityPost(channelUrl, communityPostWriteDTO);

        return "redirect:/feelog.com/@"+channelUrl+"/community";
    }

    // 2025.04.26 조승찬 :: 커뮤니티 글 읽어오기
    @ResponseBody
    @GetMapping("/@{channelUrl}/community/{postId}")
    public ResponseEntity<Map<String, Object>> getCommunityPostDetail(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                                                      @PathVariable String channelUrl, @PathVariable Long postId,
                                                                      SixRowPagination pagination) {

        CommunityPostWriteDTO postDTO = communityService.getCommunityPostDetail(postId).orElse(null);

        Map<String, Object> reponse = new HashMap<>();
        reponse.put("postDTO", postDTO);

        return ResponseEntity.ok(reponse);
    }

    // 2025.04.26 조승찬 :: 커뮤니티 글 수정하기
    @PostMapping("/@{channelUrl}/community-update")
    public String updateCommunityPost(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                      @PathVariable String channelUrl,
                                      CommunityPostWriteDTO communityPostWriteDTO, SixRowPagination pagination) {

        communityService.updateCommunityPost(communityPostWriteDTO);

        return "redirect:/feelog.com/@"+channelUrl+"/community";
    }

    // 2025.04.27 조승찬  :: 커뮤니티 글 삭제하기
    @GetMapping("/@{channelUrl}/community-delete/{postId}")
    public String deleteCommunityPost(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                      @PathVariable String channelUrl,
                                      @PathVariable Long postId, SixRowPagination pagination) {

        communityService.deleteCommunityPost(postId);

        return "redirect:/feelog.com/@"+channelUrl+"/community";
    }

    // 2025.04.27 조승찬  ::  좋아요
    @ResponseBody
    @PostMapping("/@{channelUrl}/community-like/{postId}")
    public ResponseEntity<Map<String, Object>> postCommunityPostLike(
                    @SessionAttribute(name = "member", required = false) MemberDTO member,
                    @PathVariable String channelUrl, @PathVariable Long postId,
                    SixRowPagination pagination) {

        // 좋아요 생성
        communityService.postCommunityPostLike(member.getId(), postId);

        Map<String, Object> response = new HashMap<String, Object>();
        int likeCount = communityService.getLikeCount(postId);
        response.put("likeCount", likeCount);
        return ResponseEntity.ok(response);
    }

    // 2025.04.27 조승찬  ::  좋아요 취소
    @ResponseBody
    @PostMapping("/@{channelUrl}/community-like-cancel/{postId}")
    public ResponseEntity<Map<String, Object>> cancelCommunityPostLike(
                    @SessionAttribute(name = "member", required = false) MemberDTO member,
                    @PathVariable String channelUrl, @PathVariable Long postId,
                    SixRowPagination pagination) {

        // 좋아요 취소
        communityService.cancelCommunityPostLike(member.getId(), postId);

        Map<String, Object> response = new HashMap<String, Object>();
        int likeCount = communityService.getLikeCount(postId);
        response.put("likeCount", likeCount);
        return ResponseEntity.ok(response);
    }

    // 2025.04.27 조승찬  ::  신고
    @ResponseBody
    @PostMapping("/@{channelUrl}/community-report/{postId}")
    public ResponseEntity<Map<String, Object>> postCommunityPostReport(
                    @SessionAttribute(name = "member", required = false) MemberDTO member,
                    @PathVariable String channelUrl, @PathVariable Long postId,
                    SixRowPagination pagination) {

        // 신고 생성
        communityService.postCommunityPostReport(member.getId(), postId);

        Map<String, Object> response = new HashMap<String, Object>();
        int reportCount = communityService.getReportCount(postId);
        response.put("reportCount", reportCount);
        return ResponseEntity.ok(response);
    }


    // 2025.04.27 조승찬  ::  신고 취소
    @ResponseBody
    @PostMapping("/@{channelUrl}/community-report-cancel/{postId}")
    public ResponseEntity<Map<String, Object>> cancelCommunityPostReport
                        (@SessionAttribute(name = "member", required = false) MemberDTO member,
                        @PathVariable String channelUrl, @PathVariable Long postId,
                        SixRowPagination pagination) {

        // 신고 취소
        communityService.cancelCommunityPostReport(member.getId(), postId);

        Map<String, Object> response = new HashMap<String, Object>();
        int reportCount = communityService.getReportCount(postId);
        response.put("reportCount", reportCount);
        return ResponseEntity.ok(response);
    }

    // 2025.04.28  조승찬 :: 댓글 목록
    @GetMapping("/@{channelUrl}/community-reply/{postId}")
    public String getCommunityPostReplyList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                 @PathVariable String channelUrl, @PathVariable Long postId,
                                 Model model, SixRowPagination pagination){

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        CommunityPostReplyListDTO reply = communityService.getCommunityPostReplyList(member.getId(), channelUrl, postId);

        CommunityPostWriteDTO communityPost = new CommunityPostWriteDTO();
        CommunityPostReplyDTO communityPostReply = new CommunityPostReplyDTO();
        model.addAttribute("reply", reply);
        model.addAttribute("pagination", pagination);
        model.addAttribute("currentChannelUrl",channelUrl);
        model.addAttribute("loginId", member.getId());
        model.addAttribute("communityPost", communityPost);
        model.addAttribute("communityPostReply", communityPostReply);

        return "community/community-reply";
    }

    // 2025.04.28 조승찬 :: 댓글 처리
    @PostMapping("/@{channelUrl}/community-reply")
    public String postCommunityPostReply(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                         @PathVariable String channelUrl, CommunityPostReplyDTO reply,
                                         SixRowPagination pagination) {

        log.info(reply.toString());
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        // 댓글 저장
        reply.setMemberId(member.getId());
        communityService.postCommunityPostReply(reply);

        return "redirect:/feelog.com/@"+channelUrl+"/community-reply/"+reply.getPostId();
    }
}
