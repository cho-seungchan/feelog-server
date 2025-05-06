package com.app.feelog.service.voToDto;

import com.app.feelog.domain.dto.NoticeDTO;
import com.app.feelog.domain.vo.NoticeVO;

import java.util.List;
import java.util.Optional;

public interface NoticeService {
    //    공지 목록 조회
    public default NoticeDTO toNoticeDTO(NoticeVO noticeVO) {
        NoticeDTO noticeDTO = new NoticeDTO();
        if (noticeVO != null) {
            noticeDTO.setId(noticeVO.getId());
            noticeDTO.setNoticeTitle(noticeVO.getNoticeTitle());
            noticeDTO.setNoticeContent(noticeVO.getNoticeContent());
            noticeDTO.setNoticeStatus(noticeVO.getNoticeStatus());
            noticeDTO.setUpdatedDate(noticeVO.getUpdatedDate());
            noticeDTO.setNoticeFileName(noticeVO.getNoticeFileName());
            noticeDTO.setNoticeFilePath(noticeVO.getNoticeFilePath());
            noticeDTO.setReadCount(noticeVO.getReadCount());
        }
        return noticeDTO;
    }

    public List<NoticeDTO> getNoticeListMain();

    public NoticeDTO getNoticeDetailById(Long id);

    public Optional<NoticeDTO> nextNotice(Long id);

    public Optional<NoticeDTO> previousNotice(Long id);

    public void updateReadCount(Long id);
}
