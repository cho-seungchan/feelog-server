package com.app.feelog.domain.enumeration;

public enum TagStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private final String code;

    TagStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
