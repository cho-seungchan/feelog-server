package com.app.feelog.domain.enumeration;

public enum MemberNotificationSubscribe {
    ON("설정"),
    OFF("해제");

    private String code;

    private MemberNotificationSubscribe(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
