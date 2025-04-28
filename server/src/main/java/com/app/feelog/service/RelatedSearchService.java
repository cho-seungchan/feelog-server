package com.app.feelog.service;

import com.app.feelog.domain.dto.RelatedSearchDTO;

import java.util.List;

public interface RelatedSearchService {

    void save(RelatedSearchDTO relatedSearchDTO);

    void saveRelatedSearch(String keyword, List<String> tags, Long memberId);

    List<String> getRelatedKeywords(String keyword);

}
