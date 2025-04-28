package com.app.feelog.repository;

import com.app.feelog.domain.vo.RelatedSearchVO;
import com.app.feelog.mapper.RelatedSearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RelatedSearchDAO {

    private final RelatedSearchMapper relatedSearchMapper;

    public void save(RelatedSearchVO relatedSearchVO) {
        relatedSearchMapper.insertRelatedSearch(relatedSearchVO);
    }

    public List<String> findRelatedTagsByKeyword(String keyword) {
        return relatedSearchMapper.findRelatedTagsByKeyword(keyword);
    }

}
