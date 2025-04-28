package com.app.feelog.repository;

import com.app.feelog.domain.dto.NotificationDTO;
import com.app.feelog.domain.dto.NotificationResponseDTO;
import com.app.feelog.domain.vo.NotificationVO;
import com.app.feelog.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationDAO {

    private final NotificationMapper notificationMapper;

    public void insert(NotificationVO notificationVO) {
        notificationMapper.insert(notificationVO);
    }

    public List<NotificationResponseDTO> findAllByReceiverId(Long receiverId) {
        return notificationMapper.findAllByReceiverId(receiverId);
    }

    public int countUnreadByReceiverId(Long receiverId) {
        return notificationMapper.countUnreadByReceiverId(receiverId);
    }

    public void updateAllCheckedToRead(Long receiverId){
        notificationMapper.updateAllCheckedToRead(receiverId);
    };
}
