package com.app.feelog.interceptor;

import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.NotificationResponseDTO;
import com.app.feelog.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class NotificationInterceptor implements HandlerInterceptor {

    private final NotificationService notificationService;

    public NotificationInterceptor(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null) return;

        MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");

        if (member != null) {
            Long memberId = member.getId();

            int unreadCount = notificationService.getUnreadNotificationCount(memberId);
            List<NotificationResponseDTO> latestNotifications =
                    notificationService.getNotificationsByReceiver(memberId);

            modelAndView.addObject("unreadNotificationCount", unreadCount);
            modelAndView.addObject("latestNotifications", latestNotifications);
        }
    }


}
