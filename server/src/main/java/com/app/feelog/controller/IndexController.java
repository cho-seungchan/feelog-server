package com.app.feelog.controller;

import com.app.feelog.domain.dto.*;
import com.app.feelog.domain.vo.ChannelPostScrapVO;
import com.app.feelog.service.ChannelPostService;
import com.app.feelog.service.voToDto.ChannelPostLikeService;
import com.app.feelog.service.voToDto.ChannelPostScrapService;
import com.app.feelog.util.pagination.PostPagination;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {
    private final ChannelPostService channelPostService;
    private final ChannelPostScrapService channelPostScrapService;
    private final HttpSession session;
    private final ChannelPostLikeService channelPostLikeService;

    @GetMapping("/")
    public String goToMain() {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
        return "index";
    }

    @GetMapping("/postList")
    @ResponseBody
    public ChannelPostListDTO getPostList(PostPagination pagination) {
        ChannelPostListDTO channelPostListDTO = new ChannelPostListDTO();
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        channelPostListDTO = channelPostService.getPostAll(pagination);

        if(loginMember != null){
            List<Long> scrapIds = channelPostService.getMemberScrap(loginMember.getId());
            Set<Long> scrapIdSet = new HashSet<>(scrapIds);

            channelPostListDTO.getPostList().forEach((post) -> {
                post.setScrapped(scrapIdSet.contains(post.getId()));
            });

            List<Long> likeIds = channelPostLikeService.getPostLikeByMemberId(loginMember.getId());
            Set<Long> likeIdSet = new HashSet<>(likeIds);

            channelPostListDTO.getPostList().forEach(post -> {
                post.setLiked(likeIdSet.contains(post.getId()));
            });
        }

        return channelPostListDTO;
    }

    @GetMapping("/cheerPost")
    @ResponseBody
    public MainPostListDTO getCheerPost() {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
        MainPostListDTO mainPostListDTO = channelPostService.getCheerPost();

        if(loginMember != null){
            List<Long> scrapIds = channelPostService.getMemberScrap(loginMember.getId());
            Set<Long> scrapIdSet = new HashSet<>(scrapIds);

            mainPostListDTO.setScrapped(scrapIdSet.contains(mainPostListDTO.getId()));

            List<Long> likeIds = channelPostLikeService.getPostLikeByMemberId(loginMember.getId());
            Set<Long> likeIdSet = new HashSet<>(likeIds);

            mainPostListDTO.setLiked(likeIdSet.contains(mainPostListDTO.getId()));
        }

        return mainPostListDTO;
    }

    @GetMapping("/cheerPostList")
    @ResponseBody
    public ChannelPostListDTO getCheerPostList(PostPagination pagination) {
        ChannelPostListDTO channelPostListDTO = new ChannelPostListDTO();
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        channelPostListDTO = channelPostService.getCheerPostList(pagination);

        if(loginMember != null){
            List<Long> scrapIds = channelPostService.getMemberScrap(loginMember.getId());
            Set<Long> scrapIdSet = new HashSet<>(scrapIds);

            channelPostListDTO.getPostList().forEach((post) -> {
                post.setScrapped(scrapIdSet.contains(post.getId()));
            });

            List<Long> likeIds = channelPostLikeService.getPostLikeByMemberId(loginMember.getId());
            Set<Long> likeIdSet = new HashSet<>(likeIds);

            channelPostListDTO.getPostList().forEach(post -> {
                post.setLiked(likeIdSet.contains(post.getId()));
            });
        }
        return channelPostListDTO;
    }

    @GetMapping("/post/cheer-post")
    public String goToCheerPost() {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
        return "post/cheer-post";
    }

    @PostMapping("/scrapPost")
    public void scrapPost(@RequestBody ChannelPostScrapDTO channelPostScrapDTO) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        List<ChannelPostScrapDTO> scraps = channelPostScrapService.getScrapByMemberId(loginMember.getId());

        boolean alreadyScrapped = false;

        for (ChannelPostScrapDTO scrap : scraps) {
            if (Objects.equals(scrap.getPostId(), channelPostScrapDTO.getPostId())) {
                alreadyScrapped = true;
                break;
            }
        }
        if (alreadyScrapped) {
            log.info("스크랩이 이미 되어 있어서 삭제합니다.");
            channelPostScrapService.deleteScrapByPostId(channelPostScrapDTO.getPostId());
        } else {
            log.info("스크랩이 안 되어 있어서 추가합니다.");
            channelPostScrapService.insertScrap(channelPostScrapDTO);
        }
    }

    @PostMapping("/insertChannelPostReport")
    public void addChannelPostReport (@RequestBody ChannelPostReportListDTO channelPostReportListDTO) {
        log.info(channelPostReportListDTO.toString());
        channelPostService.addReport(channelPostReportListDTO);
        channelPostService.addChannelPostReport(channelPostReportListDTO);
    }

    @GetMapping("/reportList")
    @ResponseBody
    public List<ChannelPostReportDTO> getReportList(Long id) {
        return channelPostService.getReportByMemberId(id);
    }

}
