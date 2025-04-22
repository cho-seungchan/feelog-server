package com.app.feelog.domain.enumeration;

public enum MemberNotificationPostReplyLike {
    ON("설정"),
    OFF("해제");

    private String code;

    private MemberNotificationPostReplyLike(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
