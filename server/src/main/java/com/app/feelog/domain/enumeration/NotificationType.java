package com.app.feelog.domain.enumeration;

public enum NotificationType {
    SUBSCRIBE("구독"),
    COMMUNITY_POST("커뮤니티 글 작성"),
    POST_REPLY("포스트 댓글 작성"),
    POST_REPLY_LIKE("댓글 좋아요"),
    POST_LIKE("포스트 좋아요"),
    RECEIVE_MESSAGE("메세지 수신");

    private final String code;

    NotificationType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
