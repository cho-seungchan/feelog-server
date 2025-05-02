package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.ChannelPostFileDTO;
import com.app.feelog.domain.dto.ChannelPostLikeDTO;
import com.app.feelog.domain.vo.ChannelPostFileVO;

import java.util.List;

public interface ChannelPostLikeService {
    public void addPostLike(ChannelPostLikeDTO channelPostLikeDTO);

    List<Long> getPostLikeByMemberId(Long memberId);

}
