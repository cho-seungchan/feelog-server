package com.app.feelog.repository;

import com.app.feelog.domain.vo.ChannelPostScrapVO;
import com.app.feelog.mapper.ChannelPostScrapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChannelPostScrapDAO {
    private final ChannelPostScrapMapper channelPostScrapMapper;

    public void saveScrap(ChannelPostScrapVO channelPostScrapVO){
        channelPostScrapMapper.insertScrap(channelPostScrapVO);
    };

    public List<ChannelPostScrapVO> findScrapByMemberId(Long id){
        return channelPostScrapMapper.selectScrapByMemberId(id);
    };

    public void deleteScrapByPostId(Long id){
        channelPostScrapMapper.deleteScrapByPostId(id);
    };

    public List<Long> findMemberScrap(Long id){
        return channelPostScrapMapper.selectMemberScrap(id);
    };

}
