package com.app.feelog.domain.enumeration;

public enum MemberNotificationMessage {
    ON("설정"),
    OFF("해제");

    private String code;

    private MemberNotificationMessage(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
