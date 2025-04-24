package com.app.feelog.mapper;

import com.app.feelog.domain.dto.ChannelPostListDTO;
import com.app.feelog.domain.dto.ChannelPostTagListDTO;
import com.app.feelog.domain.dto.MainPostListDTO;
import com.app.feelog.domain.dto.MemberTaskPoolDTO;
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

    @Test
    public void selectTagAllTest () {
        List<ChannelPostTagListDTO> tagList = postMapper.selectPostTagByPostId(4L);

        tagList.forEach(System.out::println);
    }
}
