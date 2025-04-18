package com.app.feelog.domain.enumeration;

public enum MemberNotificationPostLike {
    SET("설정"),
    CLEAR("해제");

    private String code;

    private MemberNotificationPostLike(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
