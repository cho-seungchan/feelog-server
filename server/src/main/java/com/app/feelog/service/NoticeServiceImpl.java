package com.app.feelog.service;

import com.app.feelog.domain.dto.NoticeDTO;
import com.app.feelog.domain.dto.NoticeListDTO;
import com.app.feelog.repository.NoticeDAO;
import com.app.feelog.repository.SubscribeDAO;
import com.app.feelog.service.voToDto.NoticeService;
import com.app.feelog.util.pagination.NoticePagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class NoticeServiceImpl implements NoticeService {
    private final NoticeDAO noticeDAO;
    private final SubscribeDAO subscribeDAO;

    //    공지 목록 추가
    public void addNotice(NoticeDTO noticeDTO) {
        noticeDAO.svaeNotice(noticeDTO.toVO());
    }

//    DTO로 전환
//    @Override
//    public List<NoticeDTO> getNoticeList(NoticePagination noticePagination) {
//        List<NoticeVO> noticeVOlist = noticeDAO.findNotice(noticePagination);
//
//        List<NoticeDTO> noticeDTOList =
//                noticeVOlist.stream()
//                        .map(this::toNoticeDTO)
//                        .collect(Collectors.toList());
//
//        return noticeDTOList;
//    }

    //    공지 전체 목록 조회(페이지네이션)
    public NoticeListDTO getNoticeLists(NoticePagination noticePagination) {
        NoticeListDTO noticeListDTO = new NoticeListDTO();

        noticePagination.create(noticeDAO.findNoticeCount());

        noticeListDTO.setNoticePagination(noticePagination);
        noticeListDTO.setNoticeList(noticeDAO.findNotice(noticePagination));

        return noticeListDTO;
    }

    public void updateNotice(NoticeDTO noticeDTO) {
        noticeDAO.setNotice(noticeDTO.toVO());
    }

    public void deleteNotice(Long id) {
        noticeDAO.deleteNotice(id);
    }

    @Override
    public List<NoticeDTO> getNoticeListMain() {
        List<NoticeDTO> noticeDTOList = noticeDAO.findNotice4().stream().map(this::toNoticeDTO).toList();
        return noticeDTOList;
    }

    @Override
    public NoticeDTO getNoticeDetailById(Long id) {
        NoticeDTO noticeDTO = toNoticeDTO(noticeDAO.findNoticeDetailById(id).orElseThrow(RuntimeException::new));
        return noticeDTO;
    }

    @Override
    public Optional<NoticeDTO> nextNotice(Long id) {
        try {
            return Optional.ofNullable(toNoticeDTO(noticeDAO.nextNotice(id).orElseThrow(RuntimeException::new)));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<NoticeDTO> previousNotice(Long id) {
        try {
            return Optional.ofNullable(toNoticeDTO(noticeDAO.previousNotice(id).orElseThrow(RuntimeException::new)));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateReadCount(Long id) {
        noticeDAO.setReadCount(id);
    }
}
