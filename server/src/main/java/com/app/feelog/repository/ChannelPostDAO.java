package com.app.feelog.repository;

import com.app.feelog.domain.vo.ChannelPostVO;
import com.app.feelog.mapper.ChannelPostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChannelPostDAO {

    private final ChannelPostMapper channelPostMapper;

    // 등록
    public void insertChannelPost(ChannelPostVO vo) {
        channelPostMapper.insertChannelPost(vo);
    }

    // 수정용 단건 조회
    public ChannelPostVO findById(Long id) {
        return channelPostMapper.findById(id);
    }

    // 수정
    public void updateChannelPost(ChannelPostVO vo) {
        channelPostMapper.updateChannelPost(vo);
    }

}
