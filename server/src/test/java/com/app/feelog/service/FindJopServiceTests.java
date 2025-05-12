package com.app.feelog.service;

import com.app.feelog.mypage.service.MyPageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
public class FindJopServiceTests {
    @Autowired
    private FindJopService findJopService;
    @Autowired
    private MyPageService myPageService;

    @Test
    public void findJop() throws IOException {
        findJopService.getFindJopData().forEach(System.out::println);
    }
}
