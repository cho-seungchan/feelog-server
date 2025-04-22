package com.app.feelog.domain.enumeration;

public enum MemberNotificationPostReply {
    ON("설정"),
    OFF("해제");

    private String code;

    private MemberNotificationPostReply(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
