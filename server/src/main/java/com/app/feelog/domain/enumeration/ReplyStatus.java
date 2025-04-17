package com.app.feelog.domain.enumeration;

public enum ReplyStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private final String code;

    ReplyStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}