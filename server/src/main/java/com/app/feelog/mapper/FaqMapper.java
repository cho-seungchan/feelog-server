package com.app.feelog.mapper;

import com.app.feelog.domain.dto.FaqAdminDTO;
import com.app.feelog.domain.vo.FaqVO;
import com.app.feelog.util.pagination.NoticePagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.maven.doxia.module.fml.model.Faq;

import java.util.List;

@Mapper
public interface FaqMapper {
    public void insertFaq(FaqVO faqVO);

    public List<FaqAdminDTO> selectFaqAll(NoticePagination noticePagination);

    public int faqCount();

    public void updateFaq(FaqVO faqVO);

    public void deleteFaq(Long id);

    // FAQ 전체 조회
    public List<FaqVO> findAll();

    // FAQ 하나 조회
    public FaqVO findById(Long id);

    List<FaqVO> findRecentFaqs(int limit);

    List<FaqVO> searchFaqs(@Param("keyword") String keyword);
}
