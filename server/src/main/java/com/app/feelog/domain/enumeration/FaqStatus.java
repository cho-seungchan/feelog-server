package com.app.feelog.domain.enumeration;

public enum FaqStatus {
    ACTIVE("정상"),
    DELETE("삭제");

    private String code;

    FaqStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
