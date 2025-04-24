package com.app.feelog.mapper;

import com.app.feelog.domain.dto.SubscribeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SubscribeMapper {

    SubscribeDTO findByMemberAndChannel(@Param("memberId") Long memberId, @Param("channelId") Long channelId);

    void insertSubscribe(@Param("channelId") Long channelId, @Param("memberId") Long memberId);

    void updateSubscribeStatus(@Param("memberId") Long memberId, @Param("channelId") Long channelId, @Param("status") String status);

    Long findChannelOwnerId(Long channelId);

}
