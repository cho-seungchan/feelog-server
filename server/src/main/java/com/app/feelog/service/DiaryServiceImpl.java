package com.app.feelog.service;

import com.app.feelog.domain.dto.*;
import com.app.feelog.domain.vo.DiaryVO;
import com.app.feelog.domain.vo.FileVO;
import com.app.feelog.repository.DiaryDAO;
import com.app.feelog.repository.DiaryFileDAO;
import com.app.feelog.repository.DiaryTagDAO;
import com.app.feelog.repository.FileDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

}