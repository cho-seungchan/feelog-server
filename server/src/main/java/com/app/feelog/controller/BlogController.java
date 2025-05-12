package com.app.feelog.controller;

import com.app.feelog.domain.dto.*;
import com.app.feelog.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/feelog.com")
@Slf4j
public class BlogController {

    private final ChannelService channelService;
    private final DiaryPreviewService diaryPreviewService;
    private final ChannelPostPreviewService channelPostPreviewService;
    private final ChannelInfoService channelInfoService;
    private final SubscribeService subscribeService;

    @GetMapping("/blog-challenge")
    public String getBlogChallenge() {
        return "blog/blog-challenge";
    }


    @GetMapping("/@{channelUrl}")
    public String getBlogHome(@PathVariable("channelUrl") String channelUrl,
                              @SessionAttribute(value = "member", required = false) MemberDTO sessionMember,
                              Model model) {

        if ("official".equalsIgnoreCase(channelUrl) || "feelog".equalsIgnoreCase(channelUrl)) {
            return "redirect:/feelog/official";
        }

        ChannelDTO channel = channelService.findByUrl(channelUrl);
        if (channel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "채널을 찾을 수 없습니다.");
        }

        Long viewerId = sessionMember != null ? sessionMember.getId() : null;

        ChannelInfoDTO channelInfo = channelInfoService.findInfoByUrl(channelUrl);
        boolean isSubscribed = viewerId != null && channelInfo != null
                && subscribeService.isSubscribed(viewerId, channelInfo.getChannelId());

        boolean isOwner = viewerId != null && viewerId.equals(channelInfo.getMemberId());

        model.addAttribute("isOwner", isOwner);
        model.addAttribute("channel", channel);
        model.addAttribute("channelInfo", channelInfo);
        model.addAttribute("viewerId", viewerId != null ? viewerId : "");
        model.addAttribute("isSubscribed", isSubscribed);

        return "blog/blog-home";
    }


    @GetMapping("/@{channelUrl}/diary")
    public String diaryTab(@PathVariable("channelUrl") String channelUrl,
                           @SessionAttribute(value = "member", required = false) MemberDTO sessionMember,
                           Model model) {


        ChannelDTO channel = channelService.findByUrl(channelUrl);
        if (channel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "채널을 찾을 수 없습니다.");
        }

        Long viewerId = sessionMember != null ? sessionMember.getId() : null;

        ChannelInfoDTO channelInfo = channelInfoService.findInfoByUrl(channelUrl);
        boolean isSubscribed = viewerId != null && channelInfo != null
                && subscribeService.isSubscribed(viewerId, channelInfo.getChannelId());

        boolean isOwner = viewerId != null && viewerId.equals(channelInfo.getMemberId());

        model.addAttribute("isOwner", isOwner);
        model.addAttribute("channel", channel);
        model.addAttribute("channelInfo", channelInfo);
        model.addAttribute("viewerId", viewerId != null ? viewerId : "");
        model.addAttribute("isSubscribed", isSubscribed);

        return "blog/blog-mind-log";
    }

    @GetMapping("/@{channelUrl}/post")
    public String postTab(@PathVariable("channelUrl") String channelUrl,
                          @SessionAttribute(value = "member", required = false) MemberDTO sessionMember,
                          Model model) {


        ChannelDTO channel = channelService.findByUrl(channelUrl);
        if (channel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "채널을 찾을 수 없습니다.");
        }

        Long viewerId = sessionMember != null ? sessionMember.getId() : null;

        ChannelInfoDTO channelInfo = channelInfoService.findInfoByUrl(channelUrl);
        boolean isSubscribed = viewerId != null && channelInfo != null
                && subscribeService.isSubscribed(viewerId, channelInfo.getChannelId());

        boolean isOwner = viewerId != null && viewerId.equals(channelInfo.getMemberId());

        model.addAttribute("isOwner", isOwner);
        model.addAttribute("channel", channel);
        model.addAttribute("channelInfo", channelInfo);
        model.addAttribute("viewerId", viewerId != null ? viewerId : "");
        model.addAttribute("isSubscribed", isSubscribed);

        return "blog/blog-post";
    }

    @GetMapping("/@{channelUrl}/cheers")
    public String cheersTab(@PathVariable("channelUrl") String channelUrl,
                            @SessionAttribute(value = "member", required = false) MemberDTO sessionMember,
                            Model model) {


        ChannelDTO channel = channelService.findByUrl(channelUrl);
        if (channel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "채널을 찾을 수 없습니다.");
        }

        Long viewerId = sessionMember != null ? sessionMember.getId() : null;

        ChannelInfoDTO channelInfo = channelInfoService.findInfoByUrl(channelUrl);
        boolean isSubscribed = viewerId != null && channelInfo != null
                && subscribeService.isSubscribed(viewerId, channelInfo.getChannelId());

        boolean isOwner = viewerId != null && viewerId.equals(channelInfo.getMemberId());

        model.addAttribute("isOwner", isOwner);
        model.addAttribute("channel", channel);
        model.addAttribute("channelInfo", channelInfo);
        model.addAttribute("viewerId", viewerId != null ? viewerId : "");
        model.addAttribute("isSubscribed", isSubscribed);

        return "blog/blog-cheer";
    }


    // 05 01 더보기
    @GetMapping("/channel/{channelUrl}/diaries")
    @ResponseBody
    public DiaryPreviewListResponseDTO getDiaryList(
            @PathVariable("channelUrl") String channelUrl,
            @RequestParam(value = "viewerId", required = false) Long viewerId,
            @RequestParam("offset") int offset,
            @RequestParam("limit") int limit
    ) {
        log.info("channelUrl = {}", channelUrl);

        List<DiaryPreviewDTO> diaries = diaryPreviewService.getVisibleDiariesWithPagination(channelUrl, viewerId, offset, limit);
        int totalCount = diaryPreviewService.countVisibleDiaries(channelUrl, viewerId);

        DiaryPreviewListResponseDTO response = new DiaryPreviewListResponseDTO();
        response.setDiaries(diaries);
        response.setTotalCount(totalCount);
        return response;
    }

    // 메인 5개 미리보기
    @GetMapping("/channel/{channelUrl}/diaries/slide")
    @ResponseBody
    public List<DiaryPreviewDTO> getDiarySlide(
            @PathVariable("channelUrl") String channelUrl,
            @RequestParam(value = "viewerId", required = false) Long viewerId
    ) {
        return diaryPreviewService.getVisibleDiariesForSlide(channelUrl, viewerId);
    }

    @GetMapping("/channel/{channelUrl}/posts")
    @ResponseBody
    public ChannelPostPreviewListResponseDTO getPostList(
            @PathVariable("channelUrl") String channelUrl,
            @RequestParam(value = "viewerId", required = false) Long viewerId,
            @RequestParam("offset") int offset,
            @RequestParam("limit") int limit
    ) {
        Long channelId = channelService.findByUrl(channelUrl).getId();
        List<ChannelPostPreviewDTO> posts = channelPostPreviewService.getVisiblePostsWithPagination(channelId, offset, limit);
        int totalCount = channelPostPreviewService.countVisiblePosts(channelId);

        ChannelPostPreviewListResponseDTO response = new ChannelPostPreviewListResponseDTO();
        response.setPosts(posts);
        response.setTotalCount(totalCount);
        return response;
    }

    @GetMapping("/channel/{channelUrl}/posts/slide")
    @ResponseBody
    public List<ChannelPostPreviewDTO> getPostSlides(@PathVariable String channelUrl) {
        Long channelId = channelService.findByUrl(channelUrl).getId();
        return channelPostPreviewService.getPostSlides(channelId);
    }

    @GetMapping("/channel/{channelUrl}/cheers")
    @ResponseBody
    public ChannelPostPreviewListResponseDTO getCheerList(
            @PathVariable("channelUrl") String channelUrl,
            @RequestParam(value = "viewerId", required = false) Long viewerId,
            @RequestParam("offset") int offset,
            @RequestParam("limit") int limit
    ) {
        Long channelId = channelService.findByUrl(channelUrl).getId();
        List<ChannelPostPreviewDTO> cheers = channelPostPreviewService.getVisibleCheersWithPagination(channelId, offset, limit);
        int totalCount = channelPostPreviewService.countVisibleCheers(channelId);

        ChannelPostPreviewListResponseDTO response = new ChannelPostPreviewListResponseDTO();
        response.setPosts(cheers);
        response.setTotalCount(totalCount);
        return response;
    }

    @GetMapping("/channel/{channelUrl}/cheers/slide")
    @ResponseBody
    public List<ChannelPostPreviewDTO> getCheerSlides(@PathVariable String channelUrl) {
        Long channelId = channelService.findByUrl(channelUrl).getId();
        return channelPostPreviewService.getCheerSlides(channelId);
    }

    @GetMapping("/@official/diary")
    public String redirectOfficialDiary() {
        return "redirect:/feelog/official";
    }
    @GetMapping("/@official/post")
    public String redirectOfficialPost() {
        return "redirect:/feelog/official";
    }
    @GetMapping("/@official/cheers")
    public String redirectOfficialCheers() {
        return "redirect:/feelog/official";
    }
    @GetMapping("/@official/community")
    public String redirectOfficialCommunity() {
        return "redirect:/feelog/official";
    }

    @GetMapping("/@feelog/diary")
    public String redirectFeelogDiary() {
        return "redirect:/feelog/official";
    }
    @GetMapping("/@feelog/post")
    public String redirectFeelogPost() {
        return "redirect:/feelog/official";
    }
    @GetMapping("/@feelog/cheers")
    public String redirectFeelogCheers() {
        return "redirect:/feelog/official";
    }
    @GetMapping("/@feelog/community")
    public String redirectFeelogCommunity() {
        return "redirect:/feelog/official";
    }
}
