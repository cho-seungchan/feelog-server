package com.app.feelog.domain.enumeration;

public enum ChannelPostScrapStatus {
    ACTIVE("정상"),
    DELETED("삭제");

    private final String code;

    ChannelPostScrapStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}