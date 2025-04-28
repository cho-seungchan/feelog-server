package com.app.feelog.mapper;

import com.app.feelog.domain.vo.ChannelPostScrapVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChannelPostScrapMapper {
    public void insertScrap(ChannelPostScrapVO channelPostScrapVO);

    public List<ChannelPostScrapVO> selectScrapByMemberId(Long id);

    public void deleteScrapByPostId(Long id);

    public List<Long> selectMemberScrap(Long id);
}
