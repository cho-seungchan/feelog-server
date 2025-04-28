package com.app.feelog.service;

import com.app.feelog.domain.dto.FaqDTO;
import com.app.feelog.domain.vo.FaqVO;

import java.util.List;

public interface FaqService {

    // 전체 FAQ 조회
    public List<FaqDTO> findAll();

    // 특정 FAQ 조회
    public FaqDTO findById(Long id);

    List<FaqDTO> findRecentFaqs(int limit);

    List<FaqDTO> searchFaqs(String keyword);


    default FaqDTO toDTO(FaqVO faqVO) {
        if (faqVO == null) return null;

        FaqDTO dto = new FaqDTO();
        dto.setId(faqVO.getId());
        dto.setFaqTitle(faqVO.getFaqTitle());
        dto.setFaqContent(faqVO.getFaqContent());
        dto.setMemberId(faqVO.getMemberId());
        dto.setFaqStatus(faqVO.getFaqStatus());
        dto.setCreatedDate(faqVO.getCreatedDate());
        dto.setUpdatedDate(faqVO.getUpdatedDate());
        return dto;
    }

}
