package com.app.feelog.domain.enumeration;

public enum MemberNotificationMessage {
    SET("설정"),
    CLEAR("해제");

    private String code;

    private MemberNotificationMessage(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
