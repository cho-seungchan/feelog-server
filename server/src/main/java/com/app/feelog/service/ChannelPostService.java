package com.app.feelog.service;

import com.app.feelog.domain.dto.*;
import com.app.feelog.domain.vo.ChannelPostFileVO;
import com.app.feelog.domain.vo.ChannelPostReportVO;
import com.app.feelog.domain.vo.ChannelPostVO;
import com.app.feelog.util.pagination.PostPagination;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ChannelPostService {

    void writeChannelPost(ChannelPostDTO dto);

    ChannelPostDTO getChannelPost(Long id);

    void updateChannelPost(ChannelPostDTO dto);

    void deleteTags(List<Long> tagIds);

    default ChannelPostDTO toDTO(ChannelPostVO vo) {
        ChannelPostDTO dto = new ChannelPostDTO();
        dto.setId(vo.getId());
        dto.setPostType(vo.getPostType());
        dto.setPostFilePath(vo.getPostFilePath());
        dto.setPostFileName(vo.getPostFileName());
        dto.setMemberId(vo.getMemberId());
        dto.setChannelId(vo.getChannelId());
        dto.setPostStatus(vo.getPostStatus());
        dto.setCreatedDate(vo.getCreatedDate());
        dto.setUpdatedDate(vo.getUpdatedDate());
        dto.setPostTitle(vo.getPostTitle());
        dto.setChannelId(vo.getChannelId());
        return dto;
    }

    default PostJkDTO toPostJkDTO(ChannelPostDTO dto) {
        PostJkDTO postDto = new PostJkDTO();
        postDto.setId(dto.getId());
        postDto.setPostTitle(dto.toVO().getPostTitle());
        postDto.setPostContent(dto.toVO().getPostContent());
        postDto.setPostStatus(dto.getPostStatus());
        postDto.setCreatedDate(dto.getCreatedDate());
        postDto.setUpdatedDate(dto.getUpdatedDate());
        return postDto;
    }

    default List<ChannelPostFileDTO> toFileDTOList(List<ChannelPostFileVO> voList) {
        List<ChannelPostFileDTO> dtoList = new ArrayList<>();
        for (ChannelPostFileVO vo : voList) {
            ChannelPostFileDTO dto = new ChannelPostFileDTO();
            dto.setId(vo.getId());
            dto.setPostId(vo.getPostId());
            dto.setCreatedDate(vo.getCreatedDate());
            dto.setUpdatedDate(vo.getUpdatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }

    default ChannelPostReportDTO toReportDTO(ChannelPostReportVO vo) {
        ChannelPostReportDTO dto = new ChannelPostReportDTO();
        dto.setId(vo.getId());
        dto.setMemberId(vo.getMemberId());
        dto.setPostId(vo.getPostId());
        dto.setCreatedDate(vo.getCreatedDate());
        dto.setUpdatedDate(vo.getUpdatedDate());
        dto.setReportStatus(vo.getReportStatus());
        return dto;
    }

    List<ChannelPostSearchDTO> getRecentChannelPosts();

//    박정근 :: 전체조회
    ChannelPostListDTO getPostAll(PostPagination pagination);

    List<ChannelPostSearchDTO> searchChannelPosts(String keyword);


    List<ChannelPostSearchDTO> searchChannelPostsCheer(String keyword);

    List<ChannelPostSearchDTO> searchMoreChannelPosts(String keyword, int limit, int offset);

    List<ChannelPostSearchDTO> searchMoreChannelPostsCheer(String keyword, int limit, int offset);

    MainPostListDTO getCheerPost();

    ChannelPostListDTO getCheerPostList(PostPagination pagination);

    List<Long> getMemberScrap(Long id);

    public void addChannelPostReport(ChannelPostReportListDTO channelPostReportListDTO);

    public void addReport(ChannelPostReportListDTO channelPostReportListDTO);

    public List<ChannelPostReportDTO> getReportByMemberId(Long id);
//    박정근 :: 포스트 상세 조회
    public ChannelPostDTO getPostByPostId(Long id);

    public Optional<ChannelPostDTO> getNextPost(@Param("channelId") Long channelId, @Param("id") Long id);

    public Optional<ChannelPostDTO> getPreviousPost(@Param("channelId") Long channelId, @Param("id") Long id);

    public void addSubscriber(@Param("memberId") Long memberId, @Param("channelId") Long channelId);

//    public SubscribeDTO getSubscribe(@Param("memberId") Long memberId, @Param("channelId") Long channelId);

    public List<MainPostListDTO> getPostRandom();
}