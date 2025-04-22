package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostFileDTO;
import com.app.feelog.repository.ChannelPostFileDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ChannelPostFileServiceImpl implements ChannelPostFileService {

    private final ChannelPostFileDAO channelPostFileDAO;

    @Override
    public void addChannelPostFile(ChannelPostFileDTO dto) {
        channelPostFileDAO.save(dto.toVO());
    }

    @Override
    public List<ChannelPostFileDTO> getFilesByPostId(Long postId) {
        return channelPostFileDAO.findByPostId(postId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void removeAllFilesByPostId(Long postId) {
        channelPostFileDAO.deleteAllByPostId(postId);
    }

}
