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
public class TagServiceImpl implements TagService {
    private final TagDAO tagDAO;

    @Override
    public void saveTag(TagDTO tagDTO) {
        TagVO tagVO = tagDTO.toVO();
        tagDAO.saveTag(tagVO);
        tagDTO.setId(tagVO.getId());
    }

    @Override
    public void saveDiaryTag(DiaryTagDTO diaryTagDTO) {
        tagDAO.saveDiaryTag(diaryTagDTO.toVO());
    }

    @Override
    public List<TagVO> findTagsByContents(String contents) {
        return tagDAO.findTagsByContents(contents);
    }

    @Override
    public void deactivateTag(Long tagId) {
        tagDAO.deactivateTag(tagId);
    }
}