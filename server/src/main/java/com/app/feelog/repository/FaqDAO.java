package com.app.feelog.repository;

import com.app.feelog.domain.dto.FaqAdminDTO;
import com.app.feelog.domain.vo.FaqVO;
import com.app.feelog.mapper.FaqMapper;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FaqDAO {
    private final FaqMapper faqMapper;

    public void saveFaq(FaqVO faqVO) {
        faqMapper.insertFaq(faqVO);
    }

    public List<FaqAdminDTO> findFaqAll(NoticePagination noticePagination) {
        return faqMapper.selectFaqAll(noticePagination);
    }

    public int faqCount() {
        return faqMapper.faqCount();
    }

    public void setFaq(FaqVO faqVO) {
        faqMapper.updateFaq(faqVO);
    }

    public void deleteFaq(Long id) {
        faqMapper.deleteFaq(id);
    }

    public List<FaqVO> findAll() { return faqMapper.findAll(); }

    public FaqVO findById(Long id) { return faqMapper.findById(id); }

    public List<FaqVO> findRecentFaqs(int limit) {
        return faqMapper.findRecentFaqs(limit);
    };

    public List<FaqVO> searchFaqs(String keyword){
        return faqMapper.searchFaqs(keyword);
    };
}
