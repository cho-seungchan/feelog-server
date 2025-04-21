package com.app.feelog.service;

import com.app.feelog.domain.dto.DiaryTagDTO;
import com.app.feelog.domain.dto.TagDTO;
import com.app.feelog.domain.vo.TagVO;

import java.util.List;

public interface TagService {
    void saveTag(TagDTO tagDTO);

    void saveDiaryTag(DiaryTagDTO diaryTagDTO);

    List<TagVO> findTagsByContents(String contents);

    void deactivateTag(Long tagId);

    default TagDTO toDTO(TagVO vo) {
        TagDTO dto = new TagDTO();
        dto.setId(vo.getId());
        dto.setContents(vo.getContents());
        dto.setTagStatus(vo.getTagStatus());
        dto.setCreatedDate(vo.getCreatedDate());
        dto.setUpdatedDate(vo.getUpdatedDate());
        return dto;
    }
}