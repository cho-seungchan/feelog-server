package com.app.feelog.domain.enumeration;

public enum NotificationChecked {
    READ("읽음"),
    UNREAD("안읽음");

    private String code;

    NotificationChecked(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
