package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostTagDTO;
import com.app.feelog.domain.enumeration.TagStatus;
import com.app.feelog.domain.vo.ChannelPostTagVO;
import com.app.feelog.repository.ChannelPostTagDAO;
import com.app.feelog.repository.TagDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ChannelPostTagServiceImpl implements ChannelPostTagService {

    private final ChannelPostTagDAO channelPostTagDAO;
    private final TagDAO tagDAO;

    @Override
    public void save(ChannelPostTagDTO dto) {
        Optional<ChannelPostTagVO> existing = channelPostTagDAO.findByTagIdAndPostId(dto.getId(), dto.getChannelPostId());

        if (existing.isPresent()) {
            // 기존에 연결된 게 있다면 status만 ACTIVE로 복원
            channelPostTagDAO.reactivateTagStatus(dto.getId(), dto.getChannelPostId());
        } else {
            // 없으면 새로 삽입
            channelPostTagDAO.save(dto.toVO());
        }
    }

    @Override
    public void removeAllTagsByChannelPostId(Long channelPostId) {
        channelPostTagDAO.deleteAllByChannelPostId(channelPostId);
    }

    @Override
    public List<String> findTagContentsByChannelPostId(Long channelPostId) {
        return channelPostTagDAO.findTagContentsByChannelPostId(channelPostId);
    }

    @Override
    public void reactivateTagStatus(Long tagId, Long channelPostId) {
        channelPostTagDAO.reactivateTagStatus(tagId, channelPostId);
    }
}
