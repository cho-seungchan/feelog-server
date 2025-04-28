package com.app.feelog.mapper;

import com.app.feelog.domain.dto.SubscribeDTO;
import com.app.feelog.domain.enumeration.SubscribeStatus;
import com.app.feelog.domain.vo.SubscribeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface SubscribeMapper {

    // 1. 구독 중인지 확인
    boolean isSubscribed(@Param("memberId") Long memberId, @Param("channelId") Long channelId);

    // 2. 구독 시작
    void subscribe(SubscribeDTO subscribeDTO);

    // 3. 구독 취소
    void unsubscribe(@Param("memberId") Long memberId, @Param("channelId") Long channelId);

    Optional<SubscribeVO> selectSubscribeOne(@Param("memberId") Long memberId, @Param("channelId") Long channelId);

    public int selectSubscribeCount(Long channelId);

    public void deleteSubscribe(@Param("memberId") Long memberId, @Param("channelId") Long channelId);

}
