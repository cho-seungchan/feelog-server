package com.app.feelog.domain.enumeration;

public enum SendMessageStatus {
    ACTIVE("정상"),
    DELETE("삭제");

    private String code;

    SendMessageStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
