package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryTagDTO;
import com.app.feelog.domain.dto.TagDTO;
import com.app.feelog.domain.vo.TagVO;
import com.app.feelog.repository.TagDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TagService {
    private final TagDAO tagDAO;

    public void saveTag(TagDTO tagDTO) {
        tagDAO.saveTag(tagDTO.toVO());
    }

    public void saveDiaryTag(DiaryTagDTO diaryTagDTO) {
        tagDAO.saveDiaryTag(diaryTagDTO.toVO());
    }

    public List<TagVO> findTagsByContents(String contents) {
        return tagDAO.findTagsByContents(contents);
    }
}
