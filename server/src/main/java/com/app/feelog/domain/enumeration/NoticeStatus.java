package com.app.feelog.domain.enumeration;

public enum NoticeStatus {
    ACTIVE("정상"),
    DELETE("삭제");

    private String code;

    NoticeStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
