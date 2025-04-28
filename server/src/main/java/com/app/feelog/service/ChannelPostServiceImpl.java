package com.app.feelog.service;

import com.app.feelog.domain.dto.*;
import com.app.feelog.domain.enumeration.TagStatus;
import com.app.feelog.domain.vo.ChannelPostFileVO;
import com.app.feelog.domain.vo.ChannelPostReportVO;
import com.app.feelog.domain.vo.ChannelPostVO;
import com.app.feelog.repository.*;
import com.app.feelog.util.pagination.PostPagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    private final TagDAO tagDAO;
    private final TagService tagService;
    private final ChannelPostTagService channelPostTagService;
    private final FileDAO fileDAO;
    private final ChannelPostScrapDAO channelPostScrapDAO;
    private final ChannelPostReportDAO channelPostReportDAO;
    private final SubscribeDAO subscribeDAO;

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

        // 3-1. 삭제된 이미지 soft delete
        if (dto.getRemovedFileNames() != null) {
            for (String fileName : dto.getRemovedFileNames()) {
                fileDAO.softDeleteByFileName(fileName);
            }
        }

        // 4. 기존 태그 제거 (soft delete → content 기준으로 tag_id 추출 후 tbl_tag 업데이트)
        if (dto.getRemovedTagContents() != null) {
            for (String content : dto.getRemovedTagContents()) {
                Long tagId = channelPostTagDAO.findTagIdByContentAndPostId(content, dto.getId());
                if (tagId != null) {
                    tagDAO.softDeleteById(tagId);
                }
            }
        }

        // 5. 새 태그 추가
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

    @Override
    public void deleteTags(List<Long> tagIds) {
        if (tagIds != null) {
            for (Long tagId : tagIds) {
                tagDAO.deleteTagById(tagId);
            }
        }
    }

    @Override
    public List<ChannelPostSearchDTO> getRecentChannelPosts() {
        List<ChannelPostSearchDTO> result = channelPostDAO.findRecentPosts();
        for (ChannelPostSearchDTO dto : result) {
            if (dto.getTags() != null && !dto.getTags().isBlank()) {
                String joined = dto.getTags();
                dto.setTagsList(Arrays.asList(joined.split(",")));
            }
        }
        return result;
    }


    //  박정근 :: 전체조회, 카운트
    @Override
    public ChannelPostListDTO getPostAll(PostPagination pagination) {
        ChannelPostListDTO postList = new ChannelPostListDTO();

        pagination.create(channelPostDAO.findPostCount());

        postList.setPostPagination(pagination);
        postList.setPostList(channelPostDAO.findPostAll(pagination));

        postList.getPostList().forEach((post)->{
            post.setTagList(channelPostDAO.findPostTagByPostId(post.getId()));
            post.setPostLikeCount(channelPostDAO.findPostLikeCountByPostId(post.getId()));
            post.setPostReplyCount(channelPostDAO.findPostReplyCountByPostId(post.getId()));
            if ( post.getPostContent() != null && ! post.getPostContent().isEmpty()) {
                post.setPostContent(extractTextOnly(post.getPostContent()));
            };

        });

        return postList;
    }

    // 정도영 :: 검색기능
    @Override
    public List<ChannelPostSearchDTO> searchChannelPosts(String keyword) {
        List<ChannelPostSearchDTO> result = channelPostDAO.searchChannelPosts(keyword);

        for (ChannelPostSearchDTO dto : result) {
            // 1. 태그 리스트 변환 + 중복 제거 + 최대 5개 제한
            if (dto.getTags() != null && !dto.getTags().isEmpty()) {
                List<String> tagList = Arrays.asList(dto.getTags().split(","));
                List<String> distinctLimitedTags = tagList.stream()
                        .distinct()
                        .limit(5)
                        .collect(Collectors.toList());
                dto.setTagsList(distinctLimitedTags);
            } else {
                dto.setTagsList(new ArrayList<>());
            }

            // 2. 본문에서 img 제거
            if (dto.getContent() != null && !dto.getContent().isEmpty()) {
                dto.setContent(extractTextOnly(dto.getContent()));
            }
        }

        return result;
    }

    @Override
    public List<ChannelPostSearchDTO> searchChannelPostsCheer(String keyword) {
        List<ChannelPostSearchDTO> result = channelPostDAO.searchChannelPostsCheer(keyword);

        for (ChannelPostSearchDTO dto : result) {
            // 1. 태그 리스트 변환 + 중복 제거 + 최대 5개 제한
            if (dto.getTags() != null && !dto.getTags().isEmpty()) {
                List<String> tagList = Arrays.asList(dto.getTags().split(","));
                List<String> distinctLimitedTags = tagList.stream()
                        .distinct()
                        .limit(5)
                        .collect(Collectors.toList());
                dto.setTagsList(distinctLimitedTags);
            } else {
                dto.setTagsList(new ArrayList<>());
            }

            // 2. 본문에서 img 제거 + p
            if (dto.getContent() != null && !dto.getContent().isEmpty()) {
                dto.setContent(extractTextOnly(dto.getContent()));
            }
        }

        return result;
    }

    @Override
    public List<ChannelPostSearchDTO> searchMoreChannelPosts(String keyword, int limit, int offset) {
        List<ChannelPostSearchDTO> result = channelPostDAO.searchMoreChannelPosts(keyword, limit, offset);

        for (ChannelPostSearchDTO dto : result) {
            if (dto.getTags() != null && !dto.getTags().isEmpty()) {
                dto.setTagsList(Arrays.asList(dto.getTags().split(",")));
            } else {
                dto.setTagsList(new ArrayList<>());
            }

            if (dto.getContent() != null && !dto.getContent().isEmpty()) {
                dto.setContent(extractTextOnly(dto.getContent()));
            }
        }

        return result;
    }

    @Override
    public List<ChannelPostSearchDTO> searchMoreChannelPostsCheer(String keyword, int limit, int offset) {
        List<ChannelPostSearchDTO> result = channelPostDAO.searchMoreChannelPostsCheer(keyword, limit, offset);
        for (ChannelPostSearchDTO dto : result) {
            if (dto.getTags() != null && !dto.getTags().isEmpty()) {
                dto.setTagsList(Arrays.asList(dto.getTags().split(",")));
            } else {
                dto.setTagsList(new ArrayList<>());
            }
            if (dto.getContent() != null && !dto.getContent().isEmpty()) {
                dto.setContent(extractTextOnly(dto.getContent()));
            }
        }
        return result;
    }

    private String extractTextOnly(String html) {
        Document doc = Jsoup.parse(html);
        doc.select("img").remove();

        return doc.body().text();
    }

    @Override
    public MainPostListDTO getCheerPost() {
        MainPostListDTO postList = new MainPostListDTO();
        postList = channelPostDAO.findCheerOne().orElseThrow(RuntimeException::new);

        postList.setTagList(channelPostDAO.findPostTagByPostId(postList.getId()));
        postList.setPostLikeCount(channelPostDAO.findPostLikeCountByPostId(postList.getId()));
        postList.setPostReplyCount(channelPostDAO.findPostReplyCountByPostId(postList.getId()));
        if ( postList.getPostContent() != null && ! postList.getPostContent().isEmpty()) {
            postList.setPostContent(extractTextOnly(postList.getPostContent()));
        };

        return postList;
    }

    @Override
    public ChannelPostListDTO getCheerPostList(PostPagination pagination) {
        ChannelPostListDTO postList = new ChannelPostListDTO();

        pagination.create(channelPostDAO.findPostCount());

        postList.setPostPagination(pagination);
        postList.setPostList(channelPostDAO.findCheerPostAll(pagination));

        postList.getPostList().forEach((post)->{
            post.setTagList(channelPostDAO.findPostTagByPostId(post.getId()));
            post.setPostLikeCount(channelPostDAO.findPostLikeCountByPostId(post.getId()));
            post.setPostReplyCount(channelPostDAO.findPostReplyCountByPostId(post.getId()));
            if ( post.getPostContent() != null && ! post.getPostContent().isEmpty()) {
                post.setPostContent(extractTextOnly(post.getPostContent()));
            };
        });

        return postList;
    }


//    박정근 :: 스크랩, 신고
    @Override
    public List<Long> getMemberScrap(Long id) {
        return channelPostScrapDAO.findMemberScrap(id);
    }

    @Override
    public void addChannelPostReport(ChannelPostReportListDTO channelPostReportListDTO) {
        channelPostReportDAO.saveChannelPostReport(channelPostReportListDTO);
    }

    @Override
    public void addReport(ChannelPostReportListDTO channelPostReportListDTO) {
        channelPostReportDAO.saveReport(channelPostReportListDTO);
    }

    @Override
    public List<ChannelPostReportDTO> getReportByMemberId(Long id) {
        List<ChannelPostReportVO> reportVOs = channelPostReportDAO.findReportByMemberId(id);
        List<ChannelPostReportDTO> result = reportVOs.stream().map(this::toReportDTO).collect(Collectors.toList());

        return result;
    }

//    박정근 :: 포스트 상세조회

    @Override
    public ChannelPostDTO getPostByPostId(Long id) {
        ChannelPostDTO post = channelPostDAO.findPostByPostId(id).orElseThrow(RuntimeException::new);

        post.setLikeCount(channelPostDAO.findPostLikeCountByPostId(id));
        post.setReplyCount(channelPostDAO.findPostReplyCountByPostId(id));
        post.setTags(channelPostTagDAO.findTagContentsByChannelPostId(id));
        post.setSubscribeCount(subscribeDAO.findSubscribeCount(post.getChannelId()));

        return post;
    }

    @Override
    public Optional<ChannelPostDTO> getNextPost(Long channelId, Long id) {
        try {
            return Optional.ofNullable(
                    toDTO(channelPostDAO.findNextPost(channelId, id)
                            .orElseThrow(RuntimeException::new)));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ChannelPostDTO> getPreviousPost(Long channelId, Long id) {
        try {
            return Optional.ofNullable(
                    toDTO(channelPostDAO.findPreviousPost(channelId, id)
                            .orElseThrow(RuntimeException::new)));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    @Override
    public void addSubscriber(Long memberId, Long channelId) {
        subscribeDAO.insertSubscribe(memberId, channelId);
    }

//    @Override
//    public SubscribeDTO getSubscribe(Long memberId, Long channelId) {
//        return subscribeDAO.findByMemberAndChannel(memberId, channelId);
//    }

    @Override
    public List<MainPostListDTO> getPostRandom() {
        List<MainPostListDTO> randomPosts = channelPostDAO.findPostRandom();
        randomPosts.forEach(post->{
            post.setTagList(channelPostDAO.findPostTagByPostId(post.getId()));
        });
        return randomPosts;
    }
}