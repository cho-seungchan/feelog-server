package com.app.feelog.service;

import com.app.feelog.domain.dto.FaqDTO;
import com.app.feelog.domain.dto.FaqListDTO;
import com.app.feelog.domain.vo.FaqVO;
import com.app.feelog.repository.FaqDAO;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class FaqServiceImpl implements FaqService {
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

    @Override
    public List<FaqDTO> findAll() {
        List<FaqVO> faqVOS = faqDAO.findAll();
        List<FaqDTO> faqDTOS = new ArrayList<>();

        for (FaqVO faqVO : faqVOS) {
            faqDTOS.add(toDTO(faqVO));
        }

        return faqDTOS;
    }

    @Override
    public FaqDTO findById(Long id) {
        FaqVO faqVO = faqDAO.findById(id);
        return toDTO(faqVO);
    }

    @Override
    public List<FaqDTO> findRecentFaqs(int limit) {
        return faqDAO.findRecentFaqs(limit)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<FaqDTO> searchFaqs(String keyword) {
        return faqDAO.searchFaqs(keyword)
                .stream()
                .map(this::toDTO)
                .toList();
    }
}
