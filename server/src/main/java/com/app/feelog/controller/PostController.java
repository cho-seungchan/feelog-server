package com.app.feelog.controller;

import com.app.feelog.domain.dto.*;
import com.app.feelog.service.ChannelPostService;
import com.app.feelog.service.SubscribeService;
import com.app.feelog.service.voToDto.ChannelPostReplyService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final ChannelPostService channelPostService;
    private final HttpSession session;
    private final SubscribeService subscribeService;
    private final ChannelPostReplyService channelPostReplyService;

    @GetMapping("/read")
    public String goToRead(Model model, @RequestParam Long id) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
        ChannelPostDTO post = channelPostService.getPostByPostId(id);
        if(loginMember != null){
            SubscribeDTO subscribePost = subscribeService.getSubscribeOne(loginMember.getId(), post.getChannelId()).orElse(null);

            if(subscribePost != null){
                post.setSubscribeDTO(subscribePost);
                boolean alreadySubscribed = false;
                    if(Objects.equals(subscribePost.getMemberId(), loginMember.getId())) {
                        alreadySubscribed = true;
                    }

                post.setSubscribe(alreadySubscribed);
            }
        }


        model.addAttribute("post", post);

        return "/post/read";
    };

    @GetMapping("/nextPost")
    @ResponseBody
    public ChannelPostDTO nextPost(@RequestParam("channelId")Long channelId, @RequestParam("id") Long id) {
        Optional<ChannelPostDTO> nextPost = channelPostService.getNextPost(channelId, id);
        if(nextPost.isEmpty()){
            return null;
        }
        return nextPost.orElse(null);
    }

    @GetMapping("/previousPost")
    @ResponseBody
    public ChannelPostDTO previousPost(@RequestParam("channelId")Long channelId, @RequestParam("id") Long id) {
        Optional<ChannelPostDTO> previousPost = channelPostService.getPreviousPost(channelId, id);
        if(previousPost.isEmpty()){
            return null;
        }

        return previousPost.orElse(null);
    }

    @PostMapping("addSubscribe")
    public void addSubscribe(@RequestBody Map<String, Long> requestData) {
        Long channelId = requestData.get("channelId"); // JSON에서 채널 ID 추출
        log.info("channelId: {}", channelId);
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        channelPostService.addSubscriber(loginMember.getId(), channelId);
    }

    @PutMapping("deleteSubscribe")
    public void deleteSubscribe(@RequestBody Map<String, Long> requestData) {
        Long channelId = requestData.get("channelId");
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        subscribeService.deleteSubscribe(loginMember.getId(), channelId);
    }

    @GetMapping("/randomPost")
    @ResponseBody
    public List<MainPostListDTO> getRandomPost(){
        List<MainPostListDTO> randomPost = channelPostService.getPostRandom();
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
        if(loginMember != null){
            List<Long> scrapIds = channelPostService.getMemberScrap(loginMember.getId());
            Set<Long> scrapIdSet = new HashSet<>(scrapIds);

            randomPost.forEach((post) -> {
                post.setScrapped(scrapIdSet.contains(post.getId()));
            });
        }
        return randomPost;
    }

    @PostMapping("addReply")
    public void addReply(@RequestBody ChannelPostReplyDTO channelPostReplyDTO) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
        log.info("postMemberId {}", channelPostReplyDTO.getPostMemberId());
//        channelPostReplyService.addPostReply(channelPostReplyDTO);

        Long myId = loginMember.getId();
        Long postOwnerId = channelPostReplyDTO.getPostMemberId();

        Long postReplyId = loginMember.getId();

        if (!myId.equals(postOwnerId)) {
            notificationService.sendPostReplyNotification(myId, postOwnerId, postReplyId);
        }
    }
}
