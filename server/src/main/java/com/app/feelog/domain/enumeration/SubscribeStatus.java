package com.app.feelog.domain.enumeration;

public enum SubscribeStatus {
    ACTIVE("정상"),
    DELETE("삭제");

    private String code;

    SubscribeStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
