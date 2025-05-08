package com.app.feelog.controller;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.NoticeDTO;
import com.app.feelog.domain.dto.SubscribeDTO;
import com.app.feelog.service.ChannelPostService;
import com.app.feelog.service.SubscribeService;
import com.app.feelog.service.voToDto.NoticeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
@Slf4j
public class NoticeController {
    private final NoticeService noticeService;
    private final ChannelPostService channelPostService;
    private final SubscribeService subscribeService;
    private final HttpSession session;

    @GetMapping("/notice")
    public String goToNotice(@RequestParam Long id, Model model) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        noticeService.updateReadCount(id);

        NoticeDTO noticeDTO = noticeService.getNoticeDetailById(id);
        if (loginMember != null) {
            List<Long> subscribeIds = subscribeService.getSubscribeIdsByMemberId(loginMember.getId());
            Set<Long> subscribeIdSet = new HashSet<>(subscribeIds);

            noticeDTO.setSubscribed(subscribeIdSet.contains(4L));
        }


        model.addAttribute("notice", noticeDTO);

        return "notice/notice";
    }

    @GetMapping("/noticeListMain")
    @ResponseBody
    public List<NoticeDTO> getNoticeListMain() {
        return noticeService.getNoticeListMain();
    }

    @GetMapping("/nextNotice")
    @ResponseBody
    public NoticeDTO getNextNotice(Long id) {
        Optional<NoticeDTO> noticeDTO = noticeService.nextNotice(id);
        if (noticeDTO.isEmpty()) {
            return null;
        }
        return noticeDTO.orElseThrow(RuntimeException::new);
    }

    @GetMapping("/previousNotice")
    @ResponseBody
    public NoticeDTO getPreviousNotice(Long id) {
        Optional<NoticeDTO> noticeDTO = noticeService.previousNotice(id);
        if (noticeDTO.isEmpty()) {
            return null;
        }
        return noticeDTO.orElseThrow(RuntimeException::new);
    }

    @PostMapping("/notice")
    public void addSubscribe(@RequestBody SubscribeDTO subscribeDTO) {
        log.info("addSubscribe {}", subscribeDTO);
        channelPostService.addSubscriber(subscribeDTO.getMemberId(), subscribeDTO.getChannelId());
    }

    @PostMapping("deleteSubscribe")
    public void deleteSubscribe(@RequestBody SubscribeDTO subscribeDTO) {
        log.info("deleteSubscribe {}", subscribeDTO);
        subscribeService.deleteSubscribe(subscribeDTO.getMemberId(), subscribeDTO.getChannelId());
    }
}
