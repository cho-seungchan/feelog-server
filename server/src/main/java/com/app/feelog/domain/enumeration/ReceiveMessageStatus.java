package com.app.feelog.domain.enumeration;

public enum ReceiveMessageStatus {
    ACTIVE("정상"),
    DELETE("삭제");

    private String code;

    ReceiveMessageStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
