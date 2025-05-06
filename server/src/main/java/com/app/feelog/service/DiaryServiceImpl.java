package com.app.feelog.service;

import com.app.feelog.domain.dto.*;
import com.app.feelog.domain.dto.joinDTO.DiaryDetailDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryJoinDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryPaginationDTO;
import com.app.feelog.domain.vo.DiaryVO;
import com.app.feelog.repository.*;
import com.app.feelog.util.pagination.PostPagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class DiaryServiceImpl implements DiaryService {

    private final DiaryDAO diaryDAO;
    private final DiaryFileServiceImpl diaryFileServiceImpl;
    private final DiaryTagServiceImpl diaryTagServiceImpl;
    private final TagServiceImpl tagServiceImpl;
    private final DiaryTagDAO diaryTagDAO;
    private final FileDAO fileDAO;
    private final DiaryFileDAO diaryFileDAO;
    private final DiaryLikeDAO diaryLikeDAO;
    private final DiaryReportDAO diaryReportDAO;

    @Override
    @Transactional
    public Long writeDiary(DiaryDTO diaryDTO) {
        DiaryVO diaryVO = diaryDTO.toVO();
        diaryDAO.save(diaryVO);
        Long diaryId = diaryVO.getId();

        // 첨부파일 처리 (tbl_diary_file에 file_id로 연결)
        if (diaryDTO.getFileIds() != null) {
            for (Long fileId : diaryDTO.getFileIds()) {
                DiaryFileDTO dto = new DiaryFileDTO();
                dto.setDiaryId(diaryId);
                dto.setId(fileId);
                diaryFileServiceImpl.addDiaryFile(dto);
            }
        }

        // 태그 처리
        if (diaryDTO.getTags() != null) {
            for (String content : diaryDTO.getTags()) {
                TagDTO tagDTO = new TagDTO();
                tagDTO.setContents(content);
                tagServiceImpl.saveTag(tagDTO);

                DiaryTagDTO diaryTagDTO = new DiaryTagDTO();
                diaryTagDTO.setTagId(tagDTO.getId());
                diaryTagDTO.setDiaryId(diaryId);
                diaryTagServiceImpl.save(diaryTagDTO);
            }
        }

        return diaryId;
    }


    @Override
    @Transactional(readOnly = true)
    public DiaryDTO getDiary(Long id) {
        DiaryVO diaryVO = diaryDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 다이어리를 찾을 수 없습니다."));

        DiaryDTO diaryDTO = toDTO(diaryVO);

        List<String> tags = diaryTagDAO.findTagContentsByDiaryId(id);
        diaryDTO.setTags(tags);

        return diaryDTO;
    }


    @Override
    public void updateDiary(DiaryDTO diaryDTO) {
        DiaryVO diaryVO = diaryDTO.toVO();
        diaryDAO.update(diaryVO);

        diaryFileServiceImpl.removeAllFilesByDiaryId(diaryVO.getId());
        if (diaryDTO.getFileIds() != null) {
            for (Long fileId : diaryDTO.getFileIds()) {
                DiaryFileDTO dto = new DiaryFileDTO();
                dto.setDiaryId(diaryVO.getId());
                dto.setId(fileId);
                diaryFileServiceImpl.addDiaryFile(dto);
            }
        }

        diaryTagServiceImpl.removeAllTagsByDiaryId(diaryVO.getId());
        if (diaryDTO.getTags() != null) {
            for (String content : diaryDTO.getTags()) {
                TagDTO tagDTO = new TagDTO();
                tagDTO.setContents(content);
                tagServiceImpl.saveTag(tagDTO);

                DiaryTagDTO diaryTagDTO = new DiaryTagDTO();
                diaryTagDTO.setTagId(tagDTO.getId());
                diaryTagDTO.setDiaryId(diaryVO.getId());
                diaryTagServiceImpl.save(diaryTagDTO);
            }
        }
    }

    @Override
    public List<DiarySearchDTO> getRecentDiaries() {
        List<DiarySearchDTO> result = diaryDAO.getRecentDiaries();
        for (DiarySearchDTO dto : result) {
            if (dto.getTags() != null && !dto.getTags().isEmpty()) {
                dto.setTagsList(Arrays.asList(dto.getTags().split(",")));
            }
        }
        return result;
    }

    @Override
    public List<DiarySearchDTO> searchDiaries(String keyword) {
        List<DiarySearchDTO> result = diaryDAO.searchDiaries(keyword);

        for (DiarySearchDTO dto : result) {
            // 1. 태그 가공
            if (dto.getTags() != null && !dto.getTags().isEmpty()) {
                dto.setTagsList(Arrays.asList(dto.getTags().split(",")));
            } else {
                dto.setTagsList(new ArrayList<>());
            }

            // 2. 본문 내용에서 img 제거
            if (dto.getContent() != null && !dto.getContent().isEmpty()) {
                String filteredContent = extractTextOnly(dto.getContent());
                dto.setContent(filteredContent);
            }
        }

        return result;
    }

    @Override
    public List<DiarySearchDTO> searchMoreDiaries(String keyword, int limit, int offset) {
        List<DiarySearchDTO> result = diaryDAO.searchMoreDiaries(keyword, limit, offset);

        for (DiarySearchDTO dto : result) {
            // 1. 태그 가공
            if (dto.getTags() != null && !dto.getTags().isEmpty()) {
                dto.setTagsList(Arrays.asList(dto.getTags().split(",")));
            } else {
                dto.setTagsList(new ArrayList<>());
            }

            // 2. 본문 내용에서 img 제거
            if (dto.getContent() != null && !dto.getContent().isEmpty()) {
                String filteredContent = extractTextOnly(dto.getContent());
                dto.setContent(filteredContent);
            }
        }

        return result;
    }



    private String extractTextOnly(String html) {
        Document doc = Jsoup.parse(html);
        doc.select("img").remove();

        return doc.body().text();
    }

//    박정근 :: 다이어리 페이지네이션
    @Override
    public DiaryPaginationDTO getDiaryList(PostPagination postPagination) {
        DiaryPaginationDTO diaryPagination = new DiaryPaginationDTO();

        postPagination.create(diaryDAO.selectDiaryCount());

        diaryPagination.setPostPagination(postPagination);
        diaryPagination.setDiaryList(diaryDAO.findDiaryListPagination(postPagination));

        diaryPagination.getDiaryList().forEach((diary) -> {
            if ( diary.getDiaryContent() != null && ! diary.getDiaryContent().isEmpty()) {
                diary.setDiaryContent(extractTextOnly(diary.getDiaryContent()));
            };

            try {
                diary.setDiaryTags(diaryTagDAO.findTagContentsByDiaryId(diary.getId()));
            } catch (Exception e) {
                diary.setDiaryTags(new ArrayList<>());
            }

            diary.setLikeCount(diaryLikeDAO.findLikeCount(diary.getId()));
            diary.setReplyCount(diaryLikeDAO.findReplyCount(diary.getId()));
        });

        return diaryPagination;
    }

    @Override
    public List<Long> getDiaryReportIds(Long memberId) {
        return diaryReportDAO.findDiaryReportByMemberId(memberId);
    }

    @Override
    public DiaryPaginationDTO getDiaryListAllAndSubscribe(@Param("postPagination") PostPagination postPagination, @Param("memberId")Long memberId) {
        DiaryPaginationDTO diaryPagination = new DiaryPaginationDTO();

        postPagination.create(diaryDAO.selectDiaryCountAllAndSubscribe(memberId));
        diaryPagination.setPostPagination(postPagination);
        diaryPagination.setDiaryList(diaryDAO.findDiaryListPaginationAllAndSubscribe(postPagination, memberId));

        diaryPagination.getDiaryList().forEach((diary) -> {
            if ( diary.getDiaryContent() != null && ! diary.getDiaryContent().isEmpty()) {
                diary.setDiaryContent(extractTextOnly(diary.getDiaryContent()));
            };

            try {
                diary.setDiaryTags(diaryTagDAO.findTagContentsByDiaryId(diary.getId()));
            } catch (Exception e) {
                diary.setDiaryTags(new ArrayList<>());
            }

            diary.setLikeCount(diaryLikeDAO.findLikeCount(diary.getId()));
            diary.setReplyCount(diaryLikeDAO.findReplyCount(diary.getId()));
        });
        return diaryPagination;
    }

    @Override
    public DiaryPaginationDTO getDiaryListAll(PostPagination postPagination) {
        DiaryPaginationDTO diaryPagination = new DiaryPaginationDTO();

        postPagination.create(diaryDAO.findDiaryCountAll());
        diaryPagination.setPostPagination(postPagination);
        diaryPagination.setDiaryList(diaryDAO.findDiaryListPaginationAll(postPagination));

        diaryPagination.getDiaryList().forEach((diary) -> {
            if ( diary.getDiaryContent() != null && ! diary.getDiaryContent().isEmpty()) {
                diary.setDiaryContent(extractTextOnly(diary.getDiaryContent()));
            };

            try {
                diary.setDiaryTags(diaryTagDAO.findTagContentsByDiaryId(diary.getId()));
            } catch (Exception e) {
                diary.setDiaryTags(new ArrayList<>());
            }

            diary.setLikeCount(diaryLikeDAO.findLikeCount(diary.getId()));
            diary.setReplyCount(diaryLikeDAO.findReplyCount(diary.getId()));
        });
        return diaryPagination;
    }

//    박정근 :: 다이어리 상세보기
    @Override
    public DiaryDetailDTO getDiaryDetailByDiaryId(Long diaryId) {
        DiaryDetailDTO diaryDetailDTO = diaryDAO.findDiaryDetailByDiaryId(diaryId);
        log.info("DTO = {}", diaryDetailDTO);
        log.info("id ={}", diaryId);
        log.info("tags = {}", diaryTagDAO.findTagContentsByDiaryId(diaryId));

        diaryDetailDTO.setTags(diaryTagDAO.findTagContentsByDiaryId(diaryId));

        diaryDetailDTO.setDiaryLikeCount(diaryLikeDAO.findLikeCount(diaryId));
        diaryDetailDTO.setDiaryReplyCount(diaryLikeDAO.findReplyCount(diaryId));

        return diaryDetailDTO;
    }

    @Override
    public List<DiaryJoinDTO> getDiaryRandom() {
        List<DiaryJoinDTO> randomDiaryList = diaryDAO.findRandomDiary();

        randomDiaryList.forEach((diary) -> {
            diary.setLikeCount(diaryDAO.findDiaryLikeCount(diary.getId()));
            diary.setReplyCount(diaryDAO.findDiaryReplyCount(diary.getId()));
            diary.setDiaryTags(diaryTagDAO.findTagContentsByDiaryId(diary.getId()));
        });

        return randomDiaryList;
    }

    @Override
    public void addDiaryReadCount(Long diaryId) {
        diaryDAO.addReadCount(diaryId);
    }
}