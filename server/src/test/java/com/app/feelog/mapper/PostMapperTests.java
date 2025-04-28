package com.app.feelog.mapper;

import com.app.feelog.domain.dto.*;
import com.app.feelog.domain.vo.ChannelPostScrapVO;
import com.app.feelog.domain.vo.ChannelPostVO;
import com.app.feelog.domain.vo.MemberTaskPoolVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class PostMapperTests {
    @Autowired
    private ChannelPostMapper postMapper;
    @Autowired
    private ChannelPostScrapMapper channelPostScrapMapper;
    @Autowired
    private ChannelPostMapper channelPostMapper;
    @Autowired
    private SubscribeMapper subscribeMapper;

    @Test
    public void selectTagAllTest () {
        List<ChannelPostTagListDTO> tagList = postMapper.selectPostTagByPostId(4L);

        tagList.forEach(System.out::println);
    }

    @Test
    public void selectCheerOneTest () {
        Optional<MainPostListDTO> postList = postMapper.selectCheerOne();
        postList.ifPresent(System.out::println);
    }

    @Test
    public void selectScrapAllTest () {
        List<ChannelPostScrapVO> scraps = channelPostScrapMapper.selectScrapByMemberId(24L);
        scraps.forEach(System.out::println);
    }

    @Test
    public void selectNextPost () {
        Optional<ChannelPostVO> nextPost = channelPostMapper.selectNextPost(1L, 6L);
        nextPost.ifPresent(System.out::println);
    }

    @Test
    public void selectsdsaPost () {
        Optional<ChannelPostVO> nextPost = channelPostMapper.selectPreviousPost(1L, 6L);
        nextPost.ifPresent(System.out::println);
    }

    @Test
    public void insertSubscribeTest () {
        subscribeMapper.insertSubscribe(1L,23L);
    }
}
