function timeAgo(dateString) {
    const now = new Date();
    const pastDate = new Date(dateString);

    const diffTime = now - pastDate; // 밀리초 단위 차이 계산
    const diffSeconds = Math.floor(diffTime / 1000);
    const diffMinutes = Math.floor(diffSeconds / 60);
    const diffHours = Math.floor(diffMinutes / 60);
    const diffDays = Math.floor(diffHours / 24);
    const diffWeeks = Math.floor(diffDays / 7);
    const diffMonths = Math.floor(diffDays / 30);
    const diffYears = Math.floor(diffDays / 365);

    if (diffYears > 0) {
        return `${diffYears}년 전`;
    } else if (diffMonths > 0) {
        return `${diffMonths}개월 전`;
    } else if (diffWeeks > 0) {
        return `${diffWeeks}주 전`;
    } else if (diffDays > 0) {
        return `${diffDays}일 전`;
    } else if (diffHours > 0) {
        return `${diffHours}시간 전`;
    } else if (diffMinutes > 0) {
        return `${diffMinutes}분 전`;
    } else {
        return "방금 전";
    }
}
