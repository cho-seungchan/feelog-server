package com.app.feelog.service;

import com.app.feelog.domain.dto.*;
import com.app.feelog.domain.vo.ChannelPostFileVO;
import com.app.feelog.domain.vo.ChannelPostVO;
import com.app.feelog.repository.ChannelPostDAO;
import com.app.feelog.repository.ChannelPostFileDAO;
import com.app.feelog.repository.ChannelPostTagDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ChannelPostServiceImpl implements ChannelPostService {

    private final ChannelPostDAO channelPostDAO;
    private final PostJkService postJkService;

    private final ChannelPostFileServiceImpl channelPostFileServiceImpl;
    private final ChannelPostTagServiceImpl channelPostTagServiceImpl;
    private final TagServiceImpl tagServiceImpl;
    private final ChannelPostTagDAO channelPostTagDAO;
    private final ChannelPostFileDAO channelPostFileDAO;

    @Override
    public void writeChannelPost(ChannelPostDTO dto) {
        // 1. post 등록
        PostJkDTO postDto = toPostJkDTO(dto);
        postJkService.writePost(postDto);
        dto.setId(postDto.getId());

        // 2. channel_post 등록
        channelPostDAO.insertChannelPost(dto.toVO());

        // 3. 첨부파일 등록
        if (dto.getFileIds() != null) {
            for (Long fileId : dto.getFileIds()) {
                ChannelPostFileDTO fileDTO = new ChannelPostFileDTO();
                fileDTO.setPostId(dto.getId());
                fileDTO.setId(fileId);
                channelPostFileServiceImpl.addChannelPostFile(fileDTO);
            }
        }

        // 4. 태그 등록
        if (dto.getTags() != null) {
            for (String content : dto.getTags()) {
                TagDTO tagDTO = new TagDTO();
                tagDTO.setContents(content);
                tagServiceImpl.saveTag(tagDTO); // 중복 방지 로직 포함

                ChannelPostTagDTO tagMapDTO = new ChannelPostTagDTO();
                tagMapDTO.setId(tagDTO.getId()); // tag_id
                tagMapDTO.setChannelPostId(dto.getId());
                channelPostTagServiceImpl.save(tagMapDTO);
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ChannelPostDTO getChannelPost(Long id) {
        ChannelPostVO vo = channelPostDAO.findById(id);
        if (vo == null) {
            throw new IllegalArgumentException("해당 채널 포스트를 찾을 수 없습니다.");
        }

        ChannelPostDTO dto = toDTO(vo);

        // 게시글 기본 정보
        PostJkDTO postDto = postJkService.getPost(id);
        dto.setPostTitle(postDto.getPostTitle());
        dto.setPostContent(postDto.getPostContent());

        // 태그
        List<String> tags = channelPostTagDAO.findTagContentsByChannelPostId(id);
        dto.setTags(tags);

        // 파일
        List<ChannelPostFileVO> fileVOs = channelPostFileDAO.findByPostId(id);
        dto.setFileList(toFileDTOList(fileVOs));

        return dto;
    }



    @Override
    public void updateChannelPost(ChannelPostDTO dto) {
        // 1. post 수정
        PostJkDTO postDto = toPostJkDTO(dto);
        postDto.setId(dto.getId());
        postJkService.updatePost(postDto);

        // 2. channel_post 수정
        channelPostDAO.updateChannelPost(dto.toVO());

        // 3. 기존 첨부파일 제거 후 새로 추가
        channelPostFileServiceImpl.removeAllFilesByPostId(dto.getId());
        if (dto.getFileIds() != null) {
            for (Long fileId : dto.getFileIds()) {
                ChannelPostFileDTO fileDTO = new ChannelPostFileDTO();
                fileDTO.setPostId(dto.getId());
                fileDTO.setId(fileId);
                channelPostFileServiceImpl.addChannelPostFile(fileDTO);
            }
        }

        // 4. 기존 태그 제거 후 새로 추가
        channelPostTagServiceImpl.removeAllTagsByChannelPostId(dto.getId());
        if (dto.getTags() != null) {
            for (String content : dto.getTags()) {
                TagDTO tagDTO = new TagDTO();
                tagDTO.setContents(content);
                tagServiceImpl.saveTag(tagDTO); // 중복 방지 포함

                ChannelPostTagDTO tagMapDTO = new ChannelPostTagDTO();
                tagMapDTO.setId(tagDTO.getId());
                tagMapDTO.setChannelPostId(dto.getId());
                channelPostTagServiceImpl.save(tagMapDTO);
            }
        }
    }
}