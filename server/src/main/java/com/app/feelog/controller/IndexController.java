package com.app.feelog.controller;

import com.app.feelog.domain.dto.ChannelPostListDTO;
import com.app.feelog.service.ChannelPostService;
import com.app.feelog.util.pagination.PostPagination;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {
    private final ChannelPostService channelPostService;

    @GetMapping("/")
    public String getMain() {
        return "index";
    }

    @GetMapping("/postList")
    @ResponseBody
    public ChannelPostListDTO getPostList(PostPagination pagination) {
        return channelPostService.getPostAll(pagination);
    }

}
