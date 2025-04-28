package com.app.feelog.mapper;

import com.app.feelog.domain.dto.NotificationResponseDTO;
import com.app.feelog.domain.vo.NotificationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {

    void insert(NotificationVO notificationVO);

    List<NotificationResponseDTO> findAllByReceiverId(Long receiverId);

    int countUnreadByReceiverId(Long receiverId);

    void updateAllCheckedToRead(Long receiverId);
}
