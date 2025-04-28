package com.app.feelog.mypage.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
@ToString
@Getter
@Setter
public class CalculateTimeAgo {

    // 2025.04.23 조승찬 :: create data의 작성시점 변환하기
    public String calculateTimeAgo(String createdDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime createdDateTime = LocalDateTime.parse(createdDate, inputFormatter);
        LocalDateTime now = LocalDateTime.now();

        long days = ChronoUnit.DAYS.between(createdDateTime, now);
        long hours = ChronoUnit.HOURS.between(createdDateTime, now) % 24;
        long minutes = ChronoUnit.MINUTES.between(createdDateTime, now) % 60;

        if (days > 7) {
            return createdDateTime.format(outputFormatter); // 년월일만 출력
        } else if (days > 0) {
            return days + "일 전";
        } else if (hours > 0) {
            return hours + "시간 전";
        } else if (minutes > 0) {
            return minutes + "분 전";
        } else {
            return "방금 전";
        }
    }

}
