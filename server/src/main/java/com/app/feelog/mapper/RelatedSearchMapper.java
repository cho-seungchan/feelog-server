package com.app.feelog.mapper;

import com.app.feelog.domain.vo.RelatedSearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RelatedSearchMapper {

    void insertRelatedSearch(RelatedSearchVO relatedSearchVO);

    List<String> findRelatedTagsByKeyword(@Param("keyword") String keyword);

}
