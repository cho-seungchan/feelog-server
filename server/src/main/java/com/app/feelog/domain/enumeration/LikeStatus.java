package com.app.feelog.domain.enumeration;

public enum LikeStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private final String code;

    LikeStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
