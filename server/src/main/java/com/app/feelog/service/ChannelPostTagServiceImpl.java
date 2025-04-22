package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostTagDTO;
import com.app.feelog.repository.ChannelPostTagDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ChannelPostTagServiceImpl implements ChannelPostTagService {

    private final ChannelPostTagDAO channelPostTagDAO;

    @Override
    public void save(ChannelPostTagDTO dto) {
        channelPostTagDAO.save(dto.toVO());
    }

    @Override
    public void removeAllTagsByChannelPostId(Long channelPostId) {
        channelPostTagDAO.deleteAllByChannelPostId(channelPostId);
    }
}
