package com.app.feelog.mapper;

import com.app.feelog.domain.dto.ChannelSearchDTO;
import com.app.feelog.domain.vo.ChannelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ChannelMapper {

    List<ChannelSearchDTO> searchChannels(String keyword);

    List<ChannelSearchDTO> searchMoreChannels(@Param("keyword") String keyword, @Param("limit") int limit, @Param("offset") int offset);

    Long findChannelOwnerId(Long channelId);

    Optional<ChannelVO> findByMemberId(Long memberId);

    ChannelVO findByUrl(String channelUrl);

}
