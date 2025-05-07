package com.app.feelog.service;

import com.app.feelog.domain.dto.ChannelPostLikeDTO;
import com.app.feelog.domain.vo.ChannelPostLikeVO;
import com.app.feelog.repository.ChannelPostLikeDAO;
import com.app.feelog.service.voToDto.ChannelPostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChannelPostLikeServiceImpl implements ChannelPostLikeService {
    private final ChannelPostLikeDAO channelPostLikeDAO;

    @Override
    public void addPostLike(ChannelPostLikeDTO channelPostLikeDTO) {
        ChannelPostLikeVO postInfo = channelPostLikeDTO.toVO();
        ChannelPostLikeVO checkPost = channelPostLikeDAO.findPostLikeByPostAndMemberId(channelPostLikeDTO.getPostId(), channelPostLikeDTO.getMemberId());

        if (checkPost == null) {
            channelPostLikeDAO.saveSuperLike(postInfo);
            channelPostLikeDAO.savePostLike(postInfo);

            Long likeId = postInfo.getId();

            channelPostLikeDTO.setId(likeId);
        } else {
            channelPostLikeDAO.deletePostLike(postInfo.getPostId(), postInfo.getMemberId());
        }
    }

    @Override
    public List<Long> getPostLikeByMemberId(Long memberId) {
        return channelPostLikeDAO.findLikeIdsByMemberId(memberId);
    }
}
