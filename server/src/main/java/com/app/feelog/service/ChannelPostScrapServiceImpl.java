package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostScrapDTO;
import com.app.feelog.domain.vo.ChannelPostScrapVO;
import com.app.feelog.repository.ChannelPostScrapDAO;
import com.app.feelog.service.voToDto.ChannelPostScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChannelPostScrapServiceImpl implements ChannelPostScrapService {
    private final ChannelPostScrapDAO channelPostScrapDAO;

    @Override
    public void insertScrap(ChannelPostScrapDTO channelPostScrapDTO) {
        channelPostScrapDAO.saveScrap(channelPostScrapDTO.toVO());
    }

    @Override
    public List<ChannelPostScrapDTO> getScrapByMemberId(Long id) {
        List<ChannelPostScrapVO> scrapVOs = channelPostScrapDAO.findScrapByMemberId(id);
        List<ChannelPostScrapDTO> scrapDTOList = scrapVOs.stream().map(this::toChannelPostScrapDTO).collect(Collectors.toList());
        return scrapDTOList;
    }

    @Override
    public void deleteScrapByPostId(Long id) {
        channelPostScrapDAO.deleteScrapByPostId(id);
    }

}
