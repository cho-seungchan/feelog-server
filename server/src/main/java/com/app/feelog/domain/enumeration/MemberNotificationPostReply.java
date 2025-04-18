package com.app.feelog.domain.enumeration;

public enum MemberNotificationPostReply {
    SET("설정"),
    CLEAR("해제");

    private String code;

    private MemberNotificationPostReply(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
