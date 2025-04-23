package com.app.feelog.service;

import com.app.feelog.domain.dto.FaqDTO;
import com.app.feelog.domain.dto.FaqListDTO;
import com.app.feelog.repository.FaqDAO;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class FaqServiceImpl {
    private final FaqDAO faqDAO;

    public void addFaq(FaqDTO faqDTO) {
        faqDAO.saveFaq(faqDTO.toVO());
    }

    public FaqListDTO getFaqList(NoticePagination noticePagination) {
        FaqListDTO faqListDTO = new FaqListDTO();

        noticePagination.create(faqDAO.faqCount());

        faqListDTO.setNoticePagination(noticePagination);
        faqListDTO.setFaqLists(faqDAO.findFaqAll(noticePagination));

        return faqListDTO;
    }

    public void updateFaq(FaqDTO faqDTO) {
        faqDAO.setFaq(faqDTO.toVO());
    }

    public void deleteFaq(Long id) {
        faqDAO.deleteFaq(id);
    }

}
