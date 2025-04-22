package com.app.feelog.domain.enumeration;

public enum MemberNotificationPostLike {
    ON("설정"),
    OFF("해제");

    private String code;

    private MemberNotificationPostLike(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
