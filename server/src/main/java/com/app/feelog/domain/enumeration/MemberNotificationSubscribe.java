package com.app.feelog.domain.enumeration;

public enum MemberNotificationSubscribe {
    SET("설정"),
    CLEAR("해제");

    private String code;

    private MemberNotificationSubscribe(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
