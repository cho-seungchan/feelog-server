package com.app.feelog.controller;

import com.app.feelog.domain.dto.ChannelDTO;
import com.app.feelog.domain.dto.ChannelInfoDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.OfficialContentPreviewDTO;
import com.app.feelog.service.ChannelInfoService;
import com.app.feelog.service.ChannelService;
import com.app.feelog.service.OfficialContentPreviewService;
import com.app.feelog.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/feelog")
@Slf4j
public class OfficialController {

    private final OfficialContentPreviewService officialContentPreviewService;
    private final ChannelService channelService;
    private final ChannelInfoService channelInfoService;
    private final SubscribeService subscribeService;

    @GetMapping("/official")
    public String getOfficial(@SessionAttribute(value = "member", required = false) MemberDTO sessionMember,
                              Model model) {

        String channelUrl = "official";

        ChannelDTO channel = channelService.findByUrl(channelUrl);
        if (channel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "채널을 찾을 수 없습니다.");
        }

        ChannelInfoDTO channelInfo = channelInfoService.findInfoByUrl(channelUrl);

        int noticeCount = officialContentPreviewService.countNotices();
        channelInfo.setNoticeCount(noticeCount);

        Long viewerId = sessionMember != null ? sessionMember.getId() : null;
        boolean isSubscribed = viewerId != null && channelInfo != null &&
                subscribeService.isSubscribed(viewerId, channelInfo.getChannelId());

        boolean isOwner = viewerId != null && channelInfo != null &&
                viewerId.equals(channelInfo.getMemberId());

        List<OfficialContentPreviewDTO> noticeList = officialContentPreviewService.getTop5ByContentType("NOTICE");
        List<OfficialContentPreviewDTO> challengeList = officialContentPreviewService.getTop5ByContentType("CHALLENGE");

        model.addAttribute("isOwner", isOwner);
        model.addAttribute("channel", channel);
        model.addAttribute("channelInfo", channelInfo);
        model.addAttribute("viewerId", viewerId != null ? viewerId : "");
        model.addAttribute("isSubscribed", isSubscribed);

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("challengeList", challengeList);

        return "official/official";
    }

    @GetMapping("/official-notice")
    public String getOfficialNotice(@SessionAttribute(value = "member", required = false) MemberDTO sessionMember,
                                    Model model) {

        String channelUrl = "official";

        ChannelDTO channel = channelService.findByUrl(channelUrl);
        if (channel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "채널을 찾을 수 없습니다.");
        }

        ChannelInfoDTO channelInfo = channelInfoService.findInfoByUrl(channelUrl);

        int noticeCount = officialContentPreviewService.countNotices();
        channelInfo.setNoticeCount(noticeCount);

        Long viewerId = sessionMember != null ? sessionMember.getId() : null;
        boolean isSubscribed = viewerId != null && channelInfo != null &&
                subscribeService.isSubscribed(viewerId, channelInfo.getChannelId());

        boolean isOwner = viewerId != null && channelInfo != null &&
                viewerId.equals(channelInfo.getMemberId());

        List<OfficialContentPreviewDTO> noticeList = officialContentPreviewService.getTop5ByContentType("NOTICE");
        List<OfficialContentPreviewDTO> challengeList = officialContentPreviewService.getTop5ByContentType("CHALLENGE");

        model.addAttribute("isOwner", isOwner);
        model.addAttribute("channel", channel);
        model.addAttribute("channelInfo", channelInfo);
        model.addAttribute("viewerId", viewerId != null ? viewerId : "");
        model.addAttribute("isSubscribed", isSubscribed);

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("challengeList", challengeList);

        return "official/official-notice";
    }


//    @GetMapping("/official-challenge")
//    public String getOfficialChallenge() {
//        return "official/official-challenge";
//    }

    @GetMapping("/notice/slider")
    @ResponseBody
    public List<OfficialContentPreviewDTO> getNoticeSliderContent() {
        return officialContentPreviewService.getTop5ByContentType("NOTICE");
    }

    @GetMapping("/challenge/slider")
    @ResponseBody
    public List<OfficialContentPreviewDTO> getChallengeSliderContent() {
        return officialContentPreviewService.getTop5ByContentType("CHALLENGE");
    }

    @GetMapping("/notice/list-api")
    @ResponseBody
    public Map<String, Object> getNoticeListApi(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 9;
        int offset = (page - 1) * pageSize;

        List<OfficialContentPreviewDTO> notices = officialContentPreviewService.findNoticePage(pageSize, offset);
        int total = officialContentPreviewService.countNotices();

        Map<String, Object> result = new HashMap<>();
        result.put("notices", notices);
        result.put("totalCount", total);
        result.put("currentPage", page);
        result.put("pageSize", pageSize);

        return result;
    }
}
