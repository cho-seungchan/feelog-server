package com.app.feelog.controller;

import com.app.feelog.domain.dto.*;
import com.app.feelog.domain.enumeration.SubscribeStatus;
import com.app.feelog.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
@Slf4j
public class SearchController {

    private final DiaryService diaryService;
    private final ChannelPostService channelPostService;
    private final ChannelService channelService;
    private final TagService tagService;
    private final HttpSession session;
    private final SubscribeService subscribeService;
    private final MemberDTO memberDTO;
    private final RelatedSearchService relatedSearchService;

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword,
                         @RequestParam(value = "type", required = false) String type,
                         @SessionAttribute(value = "member", required = false) MemberDTO member,
                         Model model)
    {

        String safeKeyword = (keyword == null || keyword.trim().isEmpty()) ? "" : keyword;

        List<DiarySearchDTO> diaries = diaryService.searchDiaries(safeKeyword);
        List<ChannelPostSearchDTO> posts = channelPostService.searchChannelPosts(safeKeyword);
        List<ChannelSearchDTO> channels = channelService.searchChannels(safeKeyword);
//        List<ChannelPostSearchDTO> cheer = channelPostService.searchChannelPostsCheer(safeKeyword);
        List<String> recommendedTags = tagService.getPopularTags();

        List<String> relatedKeywords = relatedSearchService.getRelatedKeywords(safeKeyword);

        List<String> distinctKeywords = new ArrayList<>(new LinkedHashSet<>(relatedKeywords));
        model.addAttribute("relatedKeywords", distinctKeywords);

        System.out.println("연관 검색어 = " + distinctKeywords);

        if (member != null) {
            for (ChannelSearchDTO dto : channels) {
                dto.setSubscribed(subscribeService.isSubscribed(member.getId(), dto.getId()));
            }
        }

        model.addAttribute("diaries", diaries);
        model.addAttribute("channelPosts", posts);
//        model.addAttribute("channelPostsCheer", cheer);
        model.addAttribute("channels", channels);
        model.addAttribute("relatedKeywords", distinctKeywords);
        model.addAttribute("recommendedTags", recommendedTags);
        model.addAttribute("keyword", keyword);
        model.addAttribute("type", type);

        return "search/search";
    }

    @GetMapping("/search/diary/load")
    @ResponseBody
    public List<DiarySearchDTO> loadMoreDiaries(@RequestParam String keyword,
                                                @RequestParam int offset) {
            if (keyword == null || keyword.trim().isEmpty()) {
                keyword = "";
            }
        log.info("loadMoreDiaries called: keyword={}, offset={}", keyword, offset);
        return diaryService.searchMoreDiaries(keyword, 5, offset);
    }

//    @GetMapping("/search/post/load")
//    @ResponseBody
//    public List<ChannelPostSearchDTO> loadMoreChannelPosts(@RequestParam String keyword,
//                                                           @RequestParam int offset) {
//
//        if (keyword == null || keyword.trim().isEmpty()) {
//            keyword = "";
//        }
//
////        return channelPostService.searchMoreChannelPosts(keyword, 5, offset); ㅂㅈㄱㅈ
//    }
//
//    @GetMapping("/search/cheer/load")
//    @ResponseBody
//    public List<ChannelPostSearchDTO> loadMoreChannelPostsCheer(@RequestParam String keyword,
//                                                                @RequestParam int offset) {
//
//        if (keyword == null || keyword.trim().isEmpty()) {
//            keyword = "";
//        }
//
//        return channelPostService.searchMoreChannelPostsCheer(keyword, 5, offset);
//    }


    @GetMapping("/search/channel/load")
    @ResponseBody
    public List<ChannelSearchDTO> loadMoreChannels(@RequestParam String keyword,
                                                   @RequestParam int offset) {

        log.info("loadMoreChannels called: keyword={}, offset={}", keyword, offset);

        if (keyword == null || keyword.trim().isEmpty()) {
            keyword = "";
        }

        return channelService.searchMoreChannels(keyword, 5, offset);
    }

    @PostMapping("/related-search/save")
    @ResponseBody
    public void saveRelatedSearch(@RequestBody RelatedSearchDTO dto,
                                  @SessionAttribute(value = "member", required = false) MemberDTO member) {
        Long memberId = member != null ? member.getId() : null;
        dto.setMemberId(memberId);

        log.info("[연관검색 저장] keyword={}, tagId={}, memberId={}", dto.getKeyword(), dto.getTagId(), memberId);
        relatedSearchService.save(dto);
    }

    @PostMapping("/related-search/save-by-content")
    @ResponseBody
    public void saveRelatedSearchByContent(@RequestBody Map<String, String> request,
                                           @SessionAttribute(value = "member", required = false) MemberDTO member) {
        String keyword = request.get("keyword");
        String tag = request.get("tag");
        Long memberId = member != null ? member.getId() : null;

        relatedSearchService.saveRelatedSearch(keyword, List.of(tag), memberId);
    }
}
