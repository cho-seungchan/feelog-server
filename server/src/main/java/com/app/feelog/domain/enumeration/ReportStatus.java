package com.app.feelog.domain.enumeration;

public enum ReportStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private final String code;

    ReportStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
