package com.app.feelog.service;

import com.app.feelog.domain.dto.RelatedSearchDTO;
import com.app.feelog.domain.vo.TagVO;
import com.app.feelog.repository.RelatedSearchDAO;
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
public class RelatedSearchServiceImpl implements RelatedSearchService {

    private final RelatedSearchDAO relatedSearchDAO;
    private final TagDAO tagDAO;

    @Override
    public void save(RelatedSearchDTO relatedSearchDTO) {
        relatedSearchDAO.save(relatedSearchDTO.toVO());
    }

    @Override
    public void saveRelatedSearch(String keyword, List<String> tags, Long memberId) {
        if (tags == null || tags.isEmpty()) return;

        int limit = Math.min(tags.size(), 2);
        for (int i = 0; i < limit; i++) {
            String tagContent = tags.get(i);
            TagVO tag = tagDAO.findByContent(tagContent);
            if (tag != null) {
                RelatedSearchDTO dto = new RelatedSearchDTO();
                dto.setKeyword(keyword);
                dto.setTagId(tag.getId());
                dto.setMemberId(memberId);
                save(dto);
            }
        }
    }

    @Override
    public List<String> getRelatedKeywords(String keyword) {
        return relatedSearchDAO.findRelatedTagsByKeyword(keyword);
    }

}
